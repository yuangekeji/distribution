/*公共filter*/
angular.module('app').filter('SexFilter', function () {
    return function (param) {

        if (param == 0) {
            return '男';
        }
        if (param == 1) {
            return '女';
        }
    }
});
angular.module('app').filter('SubscribeFilter', function () {
    return function (param) {
        if (param == null || param == 0) {
            return '否';
        }
        if (param == 1) {
            return '是';
        }
    }
});
angular.module('app').filter('MaxLengthFilter', function () {
    return function (param, param1) {
        var length = param1 || 20;
        if (!param) {
            return "";
        }
        if (param.length > length) {
            return param.substring(0, length) + "...";
        }
        return param;
    }
});

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

angular.module('app').filter('getLength', function () {
    return function (param) {
        if(param==null || angular.isUndefined(param)){
            return 0;
        }else{
            return param.toString().length;
        }
    }
});


angular.module("app").filter(
    'to_trusted', ['$sce', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }]
)

angular.module("app").filter(
    'formatArray', [ function () {
        return function (array,format) {
            var _array = [];
            $.each(array,function(index,row){
                switch (format){
                    case "int":
                        _array.push(parseInt(row));
                        break;
                }
            })

            return _array;
        }
    }]
)

angular.module("app").filter(
    'character', [ function () {
        return function (obj,textLength) {
            if(angular.isUndefined(obj) || obj == null){
                return obj;
            }
          if(obj.length > textLength){
              obj = obj.substr(0,textLength - 1) + "...";
          }
            return obj;
        }
    }]
)

//把文本中的表情符号转为图片
angular.module("app").filter("emoticonFilter",function(){
    return function(text){
        text = angular.isUndefined(text) ? "" : text;
        var i = 0;
        while (i < 104) {
            text = text.replaceAll(emoticonData[i].Keyword, "<img src=\"app/common/images/emoticon/" + i + ".gif\">");
            i++;
        }
        return text;
    };
});

/*angular.module('app').filter('expDate', function ($filter) {
    return function (data) {
        var sub = new Date(data);
        sub.setMonth(sub.getMonth() + 2);
        return $filter('date')(sub, 'yyyy-MM-dd');
    }
});*/

angular.module('app').filter("dividendStatusFilter",function () {
    return function (input) {
        if(input=='1'){return '领取中'};
        if(input=='2'){return '领取完'};
    }
});

angular.module('app').filter("BonusTypeFilter",function () {
    return function (input) {
        if(input=='0'){return '销售奖'};
        if(input=='1'){return '一代奖'};
        if(input=='2'){return '二代奖'};
        if(input=='3'){return '分红包奖'};
        if(input=='4'){return '广告宣传奖'};
        if(input=='5'){return '级差奖'};
        if(input=='6'){return '全国董事奖'};
        if(input=='7'){return '工作室奖'};
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


