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
                    'app/admin/js/list.js?v=' + version
                ]
            },
            {
                name: 'adminAdd',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admin/js/add.js?v=' + version
                ]
            },
            {
                name: 'adminInfo',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admin/js/info.js?v=' + version
                ]
            },
            {
                name: 'recommend.list',
                files: [
                        'static/metronic/global/plugins/datatables/datatables.min.css',
                        'app/recommend/js/list.js?v=' + version,
                        'static/metronic/global/plugins/datatables/datatables.all.min.js',
                        'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                        'static/metronic/pages/scripts/components-date-time-pickers.min.js'
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
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
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
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name: 'dividend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/dividend/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name: 'dividend.detail',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/dividend/js/detail.js?v=' + version
                ]
            },
            {
                name:'account',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/account/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js']
            },
            {
                name:'transferReorder',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/account/js/transferReorder.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'order',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/order/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'memberCharge.list',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/memberCharge/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'memberCharge.add',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'static/metronic/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css',
                    'app/memberCharge/js/add.js?v=' + version
                ]
            },
            {
                name:'admMember',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    'app/admMember/js/list.js?v=' + version

                ]
            },
            {
                name:'admOperator',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admOperator/js/list.js?v=' + version
                ]
            },
            {
                name:'admDividend',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js',
                    'app/admDividend/js/list.js?v=' + version,
                ]
            },
            {
                name:'admDividend.detail',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admDividend/js/detail.js?v=' + version
                ]
            },
            {
                name:'admBonus',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admBonus/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'admOrder',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admOrder/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'admAdvance',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admAdvance/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'admProduct',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.js',
                    'app/admProduct/js/list.js?v=' + version
                ]
            },
            {
                name:'admProductAdd',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admProduct/js/add.js?v=' + version
                ]
            },
            {
                name:'product',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/product/js/list.js?v=' + version
                ]
            },
            {
                name:'productDetail',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/product/js/detail.js?v=' + version
                ]
            },
            {
                name:'admBasicSetting',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admBasicSetting/js/list.js?v=' + version
                ]
            },

            {
                name:'admWarning',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admWarning/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
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
            },
            {
                name:'admTreeMember',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admTreeMember/js/list.js?v=' + version
                ]
            },
            {
                name:'admTreeMemberAdd',
                files: [
                    'app/admTreeMember/js/add.js?v=' + version
                ]
            },
            {
                name: 'admHandleHistory',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admHandleHistory/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'admMemberCharge.list',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admMemberCharge/js/list.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name: 'admNotice',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admNotice/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name: 'admAddNotice',
                files: [
                    'app/admNotice/js/add.js'
                ]
            },
            {
                name:'admTransfer.list',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admTransfer/js/list.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'admMemberInfo',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admMember/js/memberInfo.js?v=' + version,
                    'static/metronic/pages/scripts/components-date-time-pickers.js'
                ]
            },
            {
                name:'accountHistory',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/account/js/accountHistory.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'searchValidate',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/member/js/searchValidate.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
                ]
            },
            {
                name:'platformHistory',
                files: [
                    'static/metronic/global/plugins/datatables/datatables.min.css',
                    'app/admWarning/js/platformHistory.js?v=' + version,
                    'static/metronic/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css',
                    'static/metronic/pages/scripts/components-date-time-pickers.min.js'
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
