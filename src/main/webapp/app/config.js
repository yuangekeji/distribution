'use strict';
angular
    .module('app.lazyLoad')
    .config(['$compileProvider', function ($compileProvider) {
        $compileProvider.imgSrcSanitizationWhitelist(/^\s*(http|https|data):/);
    }])
    .constant('APP_REQUIRES', {
        scripts: {
        },
        modules: [
            {
                name: 'home',
                files: [
                    'app/home/home.ctrl.js?v=' + version
                ]
            },
            {
                name: 'admin',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admin/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name: 'adminAdd',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admin/js/add.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name: 'recommend.list',
                files: [
                        'static/metronic/global/plugins/datatables/datatables.min.css',
                        'app/recommend/js/list.js?v=' + version,
                        'static/metronic/global/plugins/datatables/datatables.all.min.js',
                        'static/metronic/pages/scripts/components-date-time-pickers.js'
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
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'advance.add',
                files: [
                    'app/advance/js/add.js?v=' + version,
                    'static/metronic/global/plugins/fuelux/js/spinner.min.js'
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
                    'static/metronic/pages/scripts/components-date-time-pickers.js',
                    // 'app/common/pagination/dirPagination.js'
                ]
            },
            {
                name: 'dividend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/dividend/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name: 'dividend.detail',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/dividend/js/detail.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
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
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admMember',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admMember/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admOperator',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admOperator/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admDividend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admDividend/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admDividend.detail',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admDividend/js/detail.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admBonus',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admBonus/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admOrder',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admOrder/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js',
                    'app/FileSaver.js',
                    'app/FileSaver.min.js'
                ]
            },
            {
                name:'admAdvance',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admAdvance/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admProduct',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admProduct/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'product',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/product/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admBasicSetting',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admBasicSetting/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },

            {
                name:'admWarning',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admWarning/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'authFail',
                files: [
                    'app/auth/js/fail.js?v=' + version,
                ]
            },
            {
                name:'graph',
                files: [
                    'app/graph/js/graph.js?v=' + version,
                    'static/metronic/global/plugins/jstree/dist/themes/default/style.min.css',
                    'static/metronic/global/plugins/jstree/dist/jstree.min.js',
                    'static/metronic/pages/css/graph.css'
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
