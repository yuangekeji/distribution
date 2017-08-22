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