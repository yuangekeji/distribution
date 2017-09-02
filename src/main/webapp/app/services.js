/**=========================================================
 * Module: ConfirmModal
 *弹出窗口
 =========================================================*/
angular.module('app').
factory('ConfirmModal', ['$uibModal', '$q', '$sce', '$timeout', function ($uibModal, $q, $sce, $timeout) {
    angular.module('app')
           .controller('confirmModalCtrl', ['$scope', '$uibModalInstance', 'parameters', function ($scope, $uibModalInstance, parameters) {
        $scope.title = angular.isUndefined(parameters.title) ? 'common.text.title' : parameters.title;
        $scope.text = angular.isUndefined(parameters.text) ? '' : parameters.text;
        $scope.icon = angular.isUndefined(parameters.icon) ? '' : parameters.icon;
        $scope.text = $translate.instant($scope.text)
        $scope.text = $sce.trustAsHtml($scope.text);
        $scope.isCancel = true;
        if (!angular.isUndefined(parameters) && parameters.onOpened != null) {
            $uibModalInstance.opened.then(function () {
                parameters.onOpened();
            });
        }
        if (!angular.isUndefined(parameters.isCancel)) {
            $scope.isCancel = parameters.isCancel;
        }
        $scope.ok = function () {
            parameters.deferred.resolve(true);
            $uibModalInstance.dismiss('cancel');
        };
        $scope.cancel = function () {
            parameters.deferred.resolve(false);
            $uibModalInstance.dismiss('cancel');
        }
    }])

    var _modalInstance;
    return {
        open: function (obj) {
            var deferred = $q.defer();
            var $scope = null;
            if (!angular.isUndefined(obj) && !angular.isUndefined(obj.currentScope)) {
                $scope = obj.currentScope;
            }
            var _modalInstance = $uibModal.open({
                templateUrl: angular.isUndefined(obj) || angular.isUndefined(obj.templateUrl) ? 'app/common/tpl/confirm.tpl.html' : obj.templateUrl,
                controller: angular.isUndefined(obj) || angular.isUndefined(obj.controller) ? 'confirmModalCtrl' : obj.controller,
                scope: $scope,
                backdrop: 'static',
                size: angular.isUndefined(obj) || angular.isUndefined(obj.size) ? "" : obj.size,
                windowClass: angular.isUndefined(obj) || angular.isUndefined(obj.windowClass) ? "" : obj.windowClass,
                resolve: {
                    parameters: function () {
                        return {
                            deferred: deferred,
                            title: angular.isUndefined(obj) || angular.isUndefined(obj.title) ? '' : obj.title,
                            text: angular.isUndefined(obj) || angular.isUndefined(obj.text) ? '' : obj.text,
                            icon: angular.isUndefined(obj) || angular.isUndefined(obj.icon) ? '' : obj.icon,
                            isCancel: angular.isUndefined(obj) || angular.isUndefined(obj.isCancel) ? true : obj.isCancel,
                            onOpened: angular.isUndefined(obj) || angular.isUndefined(obj.onOpened) ? null : obj.onOpened,
                            obj: obj.params
                        }
                    }
                }
            });
            return {
                "modalInstance": _modalInstance,
                "promise": deferred.promise
            };//deferred.promise;
        },
        show:function(obj){
            var deferred = $q.defer();
            var _modalInstance = $uibModal.open({
                templateUrl: 'app/common/tpl/confirm.tpl.html',
                controller: 'confirmModalCtrl',
                backdrop: 'static',
                resolve: {
                    parameters: function () {
                        return {
                            deferred: deferred,
                            title: angular.isUndefined(obj.title) ? '' : obj.title,
                            text: angular.isUndefined(obj.text) ? '' : obj.text,
                            icon: angular.isUndefined(obj.icon) ? '' : obj.icon,
                            isCancel: angular.isUndefined(obj.isCancel) ? true : obj.isCancel,
                            onOpened:angular.isUndefined(obj.onOpened) ? null : obj.onOpened,
                            obj: obj.params
                        }
                    }
                }
            });
            return deferred.promise;
        },
        openTpl:function(obj){
            var deferred = $q.defer();
            $uibModal.open({
                templateUrl:obj.templateUrl,
                controller:"modalCtrl",
                scope: obj.currentScope,
                resolve: {
                    parameters: function () {
                        return {
                            deferred: deferred,
                            loadSuccess:obj.loadSuccess
                        }
                    }
                }
            });
            return deferred.promise;
        }
    }
}])

/**=========================================================
 * Module: Notify
 *js工具类库 notify.js
 =========================================================*/
// angular.module('app').service('Notify', [function(){
//     this.success = function(message){
//         $.notify('<i class=\"fa fa-check \"></i> '+message,
//             {status: 'success'});
//     }
//     this.error = function(message){
//         $.notify('<i class=\"fa fa-times-circle \"></i> '+message,
//             {status: 'danger'});
//     }
//     this.warning = function(message){
//         $.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> '+message,
//             {status: 'warning'});
//     }
// }]);


angular.module('app')
    .service('websocketService', ['$timeout', function ($timeout) {
        var self = this;
        var callbackPool = []; //onMessage分类处理函数
        var delayPool = []; //延迟处理请求
        var registerPool = [];//已注册的方法
        var data = {};
        var ws = null;
        var connected = false;

        function newWebSocket() {
            // var wsURL = "ws://10.188.199.4:8080/YIXUN_1.5_WEB/websocket/";
            var wsURL = "ws://localhost:8888";
            var wsTmp = new WebSocket(wsURL);
            wsTmp.onopen = function (evnt) {
                onOpen(evnt)
            };
            wsTmp.onmessage = function (evnt) {
                onMessage(evnt)
            };
            wsTmp.onclose = function (evnt) {
                onclose(evnt)
            };
            wsTmp.onerror = function (evnt) {
                onError(evnt)
            };
            return wsTmp;
        }

        ws = newWebSocket();

        function onOpen() {
            ws.readyState = true;
            updateStatus("onOpen : " + (connected ? 'TTRRUUEE' : 'FFAALLESS'));

            //缓存池中存在请求
            while (delayPool.length > 0) {

                var popData = delayPool.shift();
                if (popData.isReg == 1) {
                    self.register(popData.appId, popData.methodId, popData.scope, popData.callbackFuns);
                } else if(popData.isReg == 0){
                    self.unRegister(popData.appId, popData.methodId, popData.scope, popData.callbackFuns);
                }else {
                    self.send(popData.appId, popData.methodId, popData.content);
                }
            }
        }

        function onclose() {
            ws.readyState = false;
            updateStatus("onClosed : " + (connected ? 'TTRRUUEE' : 'FFAALLESS'));
            $timeout(function () {
                console.log('Reconnecting to server...')
                newWebSocket();
            }, 3000);
        }

        function onMessage(evnt) {
            //这里处理接收数据
            var evenData = JSON.parse(evnt.data);
            console.log("Received data from websocket: ", evenData);

            //          //返回处理
            //          if(evenData.appId === 'register')
            //          {
            //              if(evenData.methodId === 'unRegister'){
            //                  //方法返回成功，加入方法池
            //                  if(evenData.content.status == 'SUCCEED'){
            //                      //registerPool.push({appId: evenData.content.appId, methodId: evenData.content.methodId});
            //                      console.log("registerPool:", registerPool);
            //                  }else{//方法返回失败，重新发送请求
            //
            //                  }
            //              }
            //          }

            //执行回调函数
            //传回后端实时推送的数据
            angular.forEach(callbackPool,function(value){
                    if(value.appId === evenData.appId && value.methodId === evenData.methodId){
                        value.callback(evenData.content);
                    }
                });
        }

        function onError(evnt) {
            ws.readyState = false;
            console.log('ERROR: ', evnt);
            $timeout(function () {
                console.log('Reconnecting to server...')
                newWebSocket();
            }, 3000);
        }

        function updateStatus(status) {
            console.log(status);
        }

        //注册方法
        //注册成功后会一直监听后端推送的相应部分的数据
        //直到注销此方法
        self.register = function (appId, methodId, callbackFuns) {

            var webSocketRe = {};
            webSocketRe.appId = 'register';
            webSocketRe.methodId = 'register';
            webSocketRe.content = {
                appId: appId,
                methodId: methodId
            };

            if (ws.readyState != true){//websocket服务未打开
                webSocketRe.isReg = 1;//register
                webSocketRe.appId = appId;
                webSocketRe.methodId = methodId;
                webSocketRe.callbackFuns = callbackFuns;
                delayPool.push(webSocketRe);
                console.log("register-delayPool:", delayPool);
                console.log("sending is delay.");
                return "sending is delay.";
            } else {
                callbackPool.push({appId:appId,methodId:methodId,callback:callbackFuns});
                console.log("callbackPool:",callbackPool);
                return doSend(webSocketRe);
            }
        }

        //注销方法
        //通知后端不再推送相应数据
        self.unregister = function (appId, methodId,callbackFuns) {
            console.log("unregister");
            var webSocketRe = {};
            webSocketRe.appId = 'register';
            webSocketRe.methodId = 'unRegister';
            webSocketRe.content = {
                appId: appId,
                methodId: methodId
            };

            //websocket服务未开启
            if (ws.readyState != true){
                webSocketVo.isReg = 0;//unRegister
                delayPool.push(webSocketRe);//
                console.log("unregister is delay.");
                return "unregister is delay.";
            } else {
                console.log("sending unregister.");
                var num = 0;

                //循环检查回调函数池
                angular.forEach(callbackPool,function(value,key){
                    if(value.appId === appId && value.methodId === methodId){
                        num += 1;
                        if(value.callback === callbackFuns){
                            delete callbackPool[key];
                            num -=1;
                        }
                    }
                });
                console.log("num:",num);
                console.log("callbackpool:",callbackPool);

                //回调函数池中已经不存在此方法才真正发送websocket请求
                //通知后端不再推送相应数据
                if(num === 0 ){
                    doSend(webSocketRe);
                }
            }
        }

        //实际发送websocket请求
        function doSend(webSocketVo) {
            return ws.send(JSON.stringify(webSocketVo));
        }

        self.send = function (appId, methodId, content) {
            var webSocketVo = {};
            webSocketVo.appId = appId;
            webSocketVo.methodId = methodId;
            webSocketVo.content = content;
            if (ws.readyState == 0) {
                // webSocketVo.isReg = false;//不需要注册
                delayPool.push(webSocketVo);
                console.log("sending is delay.");
                return "sending is delay.";
            } else {
                console.log("sending is doing.");
                console.log("webSocketVo:",webSocketVo);
                return doSend(webSocketVo);
            }
        }
        return self;
    }]);

angular.module('app').factory('sessionRecoverer', ['$q', '$injector','$window', function($q, $injector,$window) {
    var sessionRecoverer = {
        request: function (config) {
            return config || $q.when(config);
        },
        // response:function(response)
        // {
        //    switch (response.status) {
        //        case (200):
        //            //if(!angular.isObject(response.data))
        //            //{
        //            //
        //            //}
        //            break;
        //        case (500):
        //            alert("服务器系统内部错误");
        //            break;
        //        case (401):
        //            alert("未登录");
        //            break;
        //        case (403):
        //            alert("无权限执行此操作");
        //            break;
        //        case (408):
        //            alert("请求超时");
        //            break;
        //        default:
        //            alert("未知错误");
        //    }
        //    return response;
        // },
        responseError: function(response) {
            // Session has expired
               switch (response.status) {
                   case (200):
                       //if(!angular.isObject(response.data))
                       //{
                       //
                       //}
                       break;
                   case (500):
                       alert("服务器系统内部错误");
                       break;
                   case (401):
                       alert("未登录");
                       break;
                   case (403):
                       alert("无权限执行此操作");
                       break;
                   case (408):
                       alert("请求超时");
                       break;
                   case (404):
                       alert("404找不到路径");
                       break;
                   default:
                       alert("未知错误");
               }

            $window.location.reload();
            // console.error( response.data.errorMessage);
            return $q.reject(response);
        }
    };
    return sessionRecoverer;
}]).config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('sessionRecoverer');
}]);

angular.module('app').service('citySelectService',["$http","$q",function($http,$q){
    return {
        "getCityListBy":function(areaCode){
            var deferred = $q.defer();
            $http({ method: 'GET',params:{areaCode:areaCode}, url :ctx+'/meeting/getMeetingArea' })
                .success(function (response, status, headers, config) {
                    deferred.resolve(response.data);
            //deferred.resolve(['大连|dalian|dl', '达县|daxian|dx', '东营|dongying|dy', '大庆|daqing|dq']);
                }).error(function (data, status, headers, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        }
    };
}]);
/**
 * 解决后退参数问题
 */
angular.module('app').service('routerService', function ($state, $sessionStorage) {
    $sessionStorage.routerStates = []
    return {
        /**
         * 跳转到下一页，并记录该页数据
         * @param param 需要缓存的数据
         */
        nextPage: function (param) {
            var bean = {
                url: $state.current.name,//URL
                param: param,//当前填写的数据
                state: $state.params//路由参数
            };
            $sessionStorage.routerStates.push(bean);
        },
        /**
         * 获取上一页的URL
         */
        getLastUrl: function () {
            var length = $sessionStorage.routerStates.length;
            if (length > 0) {
                return $sessionStorage.routerStates[length].url;
            } else {
                return '';
            }
        },
        /**
         * 获取上一页的url参数
         */
        getLastState: function () {
            var length = $sessionStorage.routerStates.length;
            if (length > 0) {
                return $sessionStorage.routerStates[length].state;
            } else {
                return {};
            }
        },
        /**
         * 获取当前页缓存的数据
         */
        getCurrentParam: function () {
            var length = $sessionStorage.routerStates.length;
            if (length > 0) {
                var param = angular.copy($sessionStorage.routerStates[length].param);
                return param;
            } else {
                return {};
            }
        },
        /**
         * 清除当前页面缓存的数据
         */
        clearCurrentState: function () {
            var length = $sessionStorage.routerStates.length;
            if (length == 0 || $state.current.name != $sessionStorage.routerStates[length].url)return;
            $sessionStorage.routerStates.pop();
        },
        /**
         * 清空所有缓存数据
         */
        clearAll: function () {
            $sessionStorage.routerStates = [];
        }
    }
});
angular.module('app').service('title', function () {
    /**
     * 解决IOS平台微信浏览器改变TITLE失败的问题
     * @param title
     */
    var page_title = function (title) {
        var body = document.getElementsByTagName('body')[0];
        document.title = title;
        var iframe = document.createElement("iframe");
        // iframe.setAttribute("src", ctx + "/resources/images/icon_close.svg");
        iframe.addEventListener('load', function () {
            setTimeout(function () {
                try {
                    iframe.removeEventListener('load');
                } catch (error) {

                }
                document.body.removeChild(iframe);
            }, 0);
        });
        try {
            document.body.appendChild(iframe);
        } catch (error) {
        }
    }
    return {
        setTitle: function (title) {
            page_title(title);
        }
    }
});
