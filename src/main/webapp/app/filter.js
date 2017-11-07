/*公共filter*/
angular.module('app').filter('dateformat', function () {
    return function (param) {
        return new Date(param).Format("yyyy-MM-dd hh:mm:ss");
    }
});
angular.module('app').filter('YYYYMMddHHmmFormat', function () {
    return function (param) {
        return new Date(param).Format("yyyy-MM-dd hh:mm");
    }
});
angular.module('app').filter('YYYYMMddFormat', function () {
    return function (param) {
        return new Date(param).Format("yyyy-MM-dd");
    }
});

angular.module('app').filter("dividendStatusFilter",function () {
    return function (input) {
        if(input=='1'){return '领取中'};
        if(input=='2'){return '领取完'};
    }
});

angular.module('app').filter("BonusTypeFilter",function () {
    return function (input) {
        if(input=='0'){return '一代奖'};
        if(input=='1'){return '二代奖'};
        if(input=='2'){return '三代奖'};
        if(input=='3'){return '分红包奖'};
        if(input=='4'){return '广告宣传奖'};
        if(input=='5'){return '级差奖'};
        if(input=='6'){return '全国董事奖'};
        if(input=='7'){return '销售部奖'};
        if(input=='8'){return '运营中心奖'};
        if(input=='9'){return '运营中心扶持奖'};
    }
});


angular.module('app').filter("recommendStatusFilter",function () {
    return function (input) {
        if(input=='Y'){return '已激活'};
        if(input=='N'){return '未激活'};
    }
});

angular.module('app').filter("warningStatusFilter",function () {
    return function (input) {
        if(input==0){return '发放失败'};
        if(input==1){return '发放成功'};
    }
});


angular.module('app').filter("PostLevelFilter",function () {
    return function (input) {
        if(input=='post_level1'){return '普通会员'};
        if(input=='post_level2'){return '主任'};
        if(input=='post_level3'){return '经理'};
        if(input=='post_level4'){return '总监'};
        if(input=='post_level5'){return '董事'};
        if(input=='post_level6'){return '全国董事'};
    }
});
