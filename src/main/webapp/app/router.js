angular.module('app').config([
    '$stateProvider', '$urlRouterProvider', '$locationProvider', 'RouteHelpersProvider',
    function ($stateProvider, $urlRouterProvider, $locationProvider, helper) {

       var defaultPage  = '/app/home';
       var roleId  = window['roleId'];
       var userStatus = window['userStatus'];

       if(roleId == 1 && userStatus == 'Y'){
           defaultPage = 'app/member/member-overview';
       }
        // $locationProvider.hashPrefix('main');
        $urlRouterProvider.otherwise(defaultPage);
        $stateProvider
            .state('app', {
                abstract: true,
                url: '/app',
                templateUrl: helper.basepath('common/layout.html')
            })
            .state('app.home', {
                url: '/home',
                templateUrl: helper.basepath('home/home.html'),
                resolve: helper.resolveFor('home'),
                controller: 'homeCtrl'
            })
          .state('app.recommend', {
                url: '/recommend-list',
                templateUrl: helper.basepath('recommend/html/list.html'),
                resolve: helper.resolveFor('recommend.list'),
                controller: 'recommendListCtrl'
         })
         .state('app.recommendAdd', {
                url: '/recommendAdd/:mobile',
                templateUrl: helper.basepath('recommend/html/add.html'),
                resolve: helper.resolveFor('recommend.add'),
                controller: 'recommendAddCtrl'
         })
         .state('app.member',{
                 url: '/member',
                 abstract: true,
                 templateUrl: helper.basepath('member/html/member.html'),
                 resolve: helper.resolveFor('member'),
                 controller: 'memberCtrl'
         })
         .state('app.member.info',{
                url: '/member-info',
                templateUrl: helper.basepath('member/html/info.html'),
                resolve: helper.resolveFor('member.info'),
                controller: 'memberInfoCtrl'
         })
         .state('app.member.overview',{
                url: '/member-overview',
                templateUrl: helper.basepath('member/html/overview.html'),
                resolve: helper.resolveFor('member.overview'),
                controller: 'memberOverviewCtrl'
         })
         .state('app.account',{
                url: '/account',
                templateUrl: helper.basepath('account/html/list.html'),
                resolve: helper.resolveFor('account'),
                controller: 'accountListCtrl'
          })
        .state('app.transferReorder',{
            url: '/transferReorder',
            templateUrl: helper.basepath('account/html/transferReorder.html'),
            resolve: helper.resolveFor('transferReorder'),
            controller: 'transferReorderCtrl'
        })
         .state('app.advance',{
                url: '/advance',
                templateUrl: helper.basepath('advance/html/list.html'),
                resolve: helper.resolveFor('advance.list'),
                controller: 'advanceListCtrl'
          })
          .state('app.advanceAdd',{
                url: '/advance-add',
                templateUrl: helper.basepath('advance/html/add.html'),
                resolve: helper.resolveFor('advance.add'),
                controller: 'advanceAddCtrl'
          })
           .state('app.bonus',{
                url: '/bonus',
                templateUrl: helper.basepath('bonus/html/list.html'),
                resolve: helper.resolveFor('bonus'),
                controller: 'bonusCtrl'
            })
            .state('app.dividend',{
                url: '/dividend',
                templateUrl: helper.basepath('dividend/html/list.html'),
                resolve: helper.resolveFor('dividend'),
                controller: 'dividendCtrl'
            })
            .state('app.dividend-detail',{
                url: '/dividend-detail/:memberId/:orderNo',
                templateUrl: helper.basepath('dividend/html/detail.html'),
                resolve: helper.resolveFor('dividend.detail'),
                controller: 'dividendDetailCtrl'
            })
            .state('app.graph',{
                url: '/graph/:nodeId',
                templateUrl: helper.basepath('graph/html/graph.html'),
                resolve: helper.resolveFor('graph'),
                controller: 'graphCtrl'
            })
           .state('app.order',{
                url: '/order',
                templateUrl: helper.basepath('order/html/list.html'),
                resolve: helper.resolveFor('order'),
                controller: 'orderCtrl'
            })
            .state('app.operator',{
                url: '/operator',
                templateUrl: helper.basepath('operator/html/apply.html'),
                resolve: helper.resolveFor('operator'),
                controller: 'operatorCtrl'
            })
            .state('app.memberCharge',{
                url: '/memberCharge',
                templateUrl: helper.basepath('memberCharge/html/list.html'),
                resolve: helper.resolveFor('memberCharge.list'),
                controller: 'memberChargeListCtrl'
            })
            .state('app.memberChargeAdd',{
                url: '/memberCharge-add',
                templateUrl: helper.basepath('memberCharge/html/add.html'),
                resolve: helper.resolveFor('memberCharge.add'),
                controller: 'memberChargeAddCtrl'
            })
            .state('app.admMember',{
                url: '/admMember',
                templateUrl: helper.basepath('admMember/html/list.html'),
                resolve: helper.resolveFor('admMember'),
                controller: 'admMemberCtrl'
            })
            .state('app.admOperator',{
                url: '/admOperator',
                templateUrl: helper.basepath('admOperator/html/list.html'),
                resolve: helper.resolveFor('admOperator'),
                controller: 'admOperatorCtrl'
            })
            .state('app.admDividend',{
                url: '/admDividend',
                templateUrl: helper.basepath('admDividend/html/list.html'),
                resolve: helper.resolveFor('admDividend'),
                controller: 'admDividendCtrl'
            })
            .state('app.admDividend-detail',{
                url: '/admDividend-detail/:memberId/:orderNo',
                templateUrl: helper.basepath('admDividend/html/detail.html'),
                resolve: helper.resolveFor('admDividend.detail'),
                controller: 'admDividendDetailCtrl'
            })
            .state('app.admBonus',{
                url: '/admBonus/:orderNo',
                templateUrl: helper.basepath('admBonus/html/list.html'),
                resolve: helper.resolveFor('admBonus'),
                controller: 'admBonusCtrl'
            })

            .state('app.admOrder',{
                url: '/admOrder',
                templateUrl: helper.basepath('admOrder/html/list.html'),
                resolve: helper.resolveFor('admOrder'),
                controller: 'admOrderCtrl'
            })
            .state('app.admAdvance',{
                url: '/admAdvance',
                templateUrl: helper.basepath('admAdvance/html/list.html'),
                resolve: helper.resolveFor('admAdvance'),
                controller: 'admAdvanceCtrl'
            })
            .state('app.product',{
                url: '/product',
                templateUrl: helper.basepath('product/html/list.html'),
                resolve: helper.resolveFor('product'),
                controller: 'productCtrl'
            })
            .state('app.productDetail',{
                url: '/productDetail/:id',
                templateUrl: helper.basepath('product/html/detail.html'),
                resolve: helper.resolveFor('productDetail'),
                controller: 'productDetailCtrl'
            })
            .state('app.admProduct',{
                url: '/admProduct',
                templateUrl: helper.basepath('admProduct/html/list.html'),
                resolve: helper.resolveFor('admProduct'),
                controller: 'admProductCtrl'
            })
            .state('app.admProductAdd',{
                url: '/admProductAdd/:id/:flag',
                templateUrl: helper.basepath('admProduct/html/add.html'),
                resolve: helper.resolveFor('admProductAdd'),
                controller: 'admProductAddCtrl'
            })
            .state('app.admBasicSetting',{
                url: '/admBasicSetting',
                templateUrl: helper.basepath('admBasicSetting/html/list.html'),
                resolve: helper.resolveFor('admBasicSetting'),
                controller: 'admBasicSettingCtrl'
            })

            .state('app.admin',{
                url: '/admin',
                templateUrl: helper.basepath('admin/html/list.html'),
                resolve: helper.resolveFor('admin'),
                controller: 'adminCtrl'
            })
            .state('app.adminAdd',{
                url: '/adminAdd',
                templateUrl: helper.basepath('admin/html/add.html'),
                resolve: helper.resolveFor('adminAdd'),
                controller: 'adminAddCtrl'
            })
            .state('app.adminInfo',{
                url: '/adminInfo',
                templateUrl: helper.basepath('admin/html/info.html'),
                resolve: helper.resolveFor('adminInfo'),
                controller: 'adminInfoCtrl'
            })
            .state('app.admWarning',{
                url: '/admWarning',
                templateUrl: helper.basepath('admWarning/html/list.html'),
                resolve: helper.resolveFor('admWarning'),
                controller: 'admWarningCtrl'
            })
            .state('app.authFail', {
                url: '/authFail',
                templateUrl: helper.basepath('auth/fail.html'),
                resolve: helper.resolveFor('authFail'),
                controller: 'authFailCtrl'
            })
            .state('app.admTreeMember', {
                url: '/admTreeMember',
                templateUrl: helper.basepath('admTreeMember/html/list.html'),
                resolve: helper.resolveFor('admTreeMember'),
                controller: 'admTreeMemberListCtrl'
            })
            .state('app.admTreeMemberAdd', {
                url: '/admTreeMember-add',
                templateUrl: helper.basepath('admTreeMember/html/add.html'),
                resolve: helper.resolveFor('admTreeMemberAdd'),
                controller: 'admTreeMemberAddCtrl'
            })
            .state('app.admHandleHistory',{
                url: '/admHandleHistory',
                templateUrl: helper.basepath('admHandleHistory/html/list.html'),
                resolve: helper.resolveFor('admHandleHistory'),
                controller: 'admHandleHistoryCtrl'
            })
            .state('app.admMemberCharge',{
                url: '/admMemberCharge',
                templateUrl: helper.basepath('admMemberCharge/html/list.html'),
                resolve: helper.resolveFor('admMemberCharge.list'),
                controller: 'admMemberChargeListCtrl'
            })
            .state('app.admNotice',{
                url: '/admNotice',
                templateUrl: helper.basepath('admNotice/html/list.html'),
                resolve: helper.resolveFor('admNotice'),
                controller: 'admNoticeCtrl'
            })
            .state('app.admAddNotice',{
                url: '/admAddNotice/:id',
                templateUrl: helper.basepath('admNotice/html/add.html'),
                resolve: helper.resolveFor('admAddNotice'),
                controller: 'admAddNoticeCtrl'
            })
            .state('app.admTransfer',{
                url: '/admTransfer',
                templateUrl: helper.basepath('admTransfer/html/list.html'),
                resolve: helper.resolveFor('admTransfer.list'),
                controller: 'admTransferListCtrl'
            })
            .state('app.admMemberInfo',{
                url: '/admMemberInfo/:memberId',
                templateUrl: helper.basepath('admMember/html/memberInfo.html'),
                resolve: helper.resolveFor('admMemberInfo'),
                controller: 'admMemberInfoCtrl'
            })
            .state('app.accountHistory',{
                url: '/accountHistory',
                templateUrl: helper.basepath('account/html/accountHistory.html'),
                resolve: helper.resolveFor('accountHistory'),
                controller: 'accountHistoryCtrl'
            })
            .state('app.searchValidate',{
                url: '/searchValidate/:url',
                templateUrl: helper.basepath('member/html/searchValidate.html'),
                resolve: helper.resolveFor('searchValidate'),
                controller: 'searchValidateCtrl'
            })
    }]);