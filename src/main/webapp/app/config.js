'use strict';
angular
    .module('app.lazyload')
    .config(['$compileProvider', function ($compileProvider) {
        $compileProvider.imgSrcSanitizationWhitelist(/^\s*(http|https|data):/);
    }])
    .constant('APP_REQUIRES', {
        scripts: {
            'extDate': ['app/common/filter/expDate.filter.js'],
            'focusDirective': ['app/common/directive/focus.directive.js']
        },
        modules: [
            {
                name: 'home',
                files: [
                    'app/common/home.ctrl.js?v=' + version
                ]
            },
            {
                name: 'recommend.list',
                files: ['app/recommend/js/list.js?v=' + version,
                        'resources/metronic/global/plugins/datatables/datatables.min.css',
                        'resources/metronic/global/plugins/datatables/datatables.all.min.js',
                        'resources/metronic/pages/scripts/components-date-time-pickers.min.js',
                        'app/common/pagination/dirPagination.js'
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
                    'app/member/js/member.js?v=' + version,
                    'resources/metronic/pages/scripts/profile.js',
                    'resources/metronic/pages/css/profile.css',
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
