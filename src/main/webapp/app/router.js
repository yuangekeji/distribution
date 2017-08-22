angular.module('app').config([
    '$stateProvider', '$urlRouterProvider', '$locationProvider', 'RouteHelpersProvider',
    function ($stateProvider, $urlRouterProvider, $locationProvider, helper) {
        // $locationProvider.hashPrefix('main');
        $urlRouterProvider.otherwise('/app/home');
        $stateProvider
            .state('app', {
                abstract: true,
                url: '/app',
                templateUrl: helper.basepath('common/layout.html')
            })
            .state('app.home', {
                url: '/home',
                templateUrl: helper.basepath('common/home.html'),
                resolve: helper.resolveFor('home'),
                controller: 'homeCtrl'
            })
          .state('app.recommend', {
            url: '/recommend-list',
            templateUrl: helper.basepath('recommend/html/list.html'),
            resolve: helper.resolveFor('recommend.list'),
            controller: 'recommendCtrl'
         })

    }]);