'use strict';
angular
    .module('app.lazyload')
    .config(['$compileProvider', function ($compileProvider) {
        $compileProvider.imgSrcSanitizationWhitelist(/^\s*(http|https|data):/);
    }])
    .constant('APP_REQUIRES', {
        scripts: {
            'angular-ui-tree': ['static/angular-ui-tree/angular-ui-tree.js']
        },
        modules: [
            {
                name: 'home',
                files: [
                    'app/home/home.ctrl.js?v=' + version
                ]
            },
            {
                name: 'recommend.list',
                files: [
                        'static/metronic/global/plugins/datatables/datatables.min.css',
                        'app/recommend/js/list.js?v=' + version,
                        'static/metronic/global/plugins/datatables/datatables.all.min.js',
                        'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                        // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name: 'recommend.add',
                files: ['app/recommend/js/add.js?v=' + version]
            },
            {
                name: 'recommend.rebate',
                files: ['app/recommend/js/rebate.js?v=' + version]
            },
            {
                name: 'recommend.success',
                files: ['app/recommend/js/success.js?v=' + version]
            },
            {
                name: 'recommend.finalSuccess',
                files: ['app/recommend/js/finalSuccess.js?v=' + version]
            },
            {
                name: 'recommend.detail',
                files: ['app/recommend/js/detail.js?v=' + version]
            },
            {
                name: 'member',
                files: [
                    'static/metronic/pages/css/profile.css',
                    'app/member/js/member.js?v=' + version,
                    'static/metronic/pages/scripts/profile.js',
                    'static/metronic/global/plugins/jquery.sparkline.min.js',
                ]
            },
            {
                name:'member.overview',
                files: [
                    'app/member/js/overview.js?v=' + version
                ]
            },
            {
                name:'member.info',
                files: [
                    'app/member/js/info.js?v=' + version
                ]
            },
            {
                name:'advance.list',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/advance/js/list.js?v=' + version,
                    // 'app/common/pagination/dirPagination.js',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'advance.add',
                files: [
                    'app/advance/js/add.js?v=' + version
                ]
            },
            {
                name:'operator',
                files: [
                    'app/operator/js/apply.js?v=' + version
                ]
            },
            {
                name: 'bonus',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/bonus/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name: 'dividend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/dividend/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name: 'dividend.detail',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/dividend/js/detail.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'account',
                files: [
                    'app/account/js/list.js?v=' + version,
                    'static/metronic/global/plugins/fuelux/js/spinner.min.js']
            },
            {
                name:'order',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/order/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admMember',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admMember/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admOperator',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admOperator/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admDividend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admDividend/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admBonus',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admBonus/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admRecommend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admRecommend/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admOrder',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admOrder/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admAdvance',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admAdvance/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admProduct',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admProduct/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admBasicSetting',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admBasicSetting/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admPermission',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admPermission/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name:'admWarning',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admWarning/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            }
        ]
    });

/**=========================================================
 * Module: helpers.js
 * Provides helper functions for routes definition
 =========================================================*/

(function () {
    'use strict';

    angular.module('app.routes').provider('RouteHelpers', RouteHelpersProvider);

    RouteHelpersProvider.$inject = ['APP_REQUIRES'];
    function RouteHelpersProvider(APP_REQUIRES) {

        /* jshint validthis:true */
        return {
            // provider access level
            basepath: basepath,
            resolveFor: resolveFor,
            // controller access level
            $get: function () {
                return {
                    basepath: basepath,
                    resolveFor: resolveFor
                };
            }
        };

        // Set here the base of the relative path
        // for all app views
        function basepath(uri) {
            return 'app/' + uri + '?v=' + version;
        }

        // Generates a resolve object by passing script names
        // previously configured in constant.APP_REQUIRES
        function resolveFor() {
            var _args = arguments;
            return {
                deps: [
                    '$ocLazyLoad',
                    '$q',
                    function ($ocLL, $q) {
                        // Creates a promise chain for each argument
                        var promise = $q.when(1); // empty promise
                        for (var i = 0, len = _args.length; i < len; i++) {
                            promise = andThen(_args[i]);
                        }
                        return promise;

                        // creates promise to chain dynamically
                        function andThen(_arg) {
                            // also support a function that returns a promise
                            if (typeof _arg === 'function')
                                return promise.then(_arg);
                            else
                                return promise
                                    .then(function () {
                                        // if is a module, pass the name. If not, pass the array
                                        var whatToLoad = getRequired(_arg);
                                        // simple error check
                                        if (!whatToLoad)
                                            return $
                                                .error('Route resolve: Bad resource name ['
                                                    + _arg
                                                    + ']');
                                        // finally, return a promise
                                        return $ocLL.load(whatToLoad);
                                    });
                        }

                        // check and returns required data
                        // analyze module items with the form [name: '', files: []]
                        // and also simple array of script files (for not angular js)
                        function getRequired(name) {
                            if (APP_REQUIRES.modules)
                                for (var m in APP_REQUIRES.modules)
                                    if (APP_REQUIRES.modules[m].name
                                        && APP_REQUIRES.modules[m].name === name)
                                        return APP_REQUIRES.modules[m];
                            return APP_REQUIRES.scripts
                                && APP_REQUIRES.scripts[name];
                        }

                    }]
            };
        } // resolveFor

    }
})();
