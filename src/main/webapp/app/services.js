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
angular.module('app').service('Notify', [function(){
    this.success = function(message){
        $.notify('<i class=\"fa fa-check \"></i> '+message,
            {status: 'success'});
    }
    this.error = function(message){
        $.notify('<i class=\"fa fa-times-circle \"></i> '+message,
            {status: 'danger'});
    }
    this.warning = function(message){
        $.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> '+message,
            {status: 'warning'});
    }
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
                       console.error("服务器系统内部错误");
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
                       console.error("未知错误");
               }
             if(response.status == 500 ){
                 $window.location.href = ctx + '/index#/app/home';
             }

             if(response.status == 403){
                 $window.location.href = ctx + '/index#/app/home'
             }
            // console.error( response.data.errorMessage);
            return $q.reject(response);
        }
    };
    return sessionRecoverer;
}]).config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('sessionRecoverer');
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
        // var body = document.getElementsByTagName('body')[0];
        document.title = title;
        // var iframe = document.createElement("iframe");
        // iframe.style.display ="none";
        // // iframe.setAttribute("src", ctx + "/resources/images/icon_close.svg");
        // iframe.addEventListener('load', function () {
        //     setTimeout(function () {
        //         try {
        //             iframe.removeEventListener('load');
        //         } catch (error) {
        //
        //         }
        //         document.body.removeChild(iframe);
        //     }, 0);
        // });
        // try {
        //     document.body.appendChild(iframe);
        // } catch (error) {
        // }
    }
    return {
        setTitle: function (title) {
            page_title(title);
        }
    }
});
