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
angular.module('app').filter('TypeFilter', function () {
    return function (param) {
        if (param == "subscribe") {
            return '订阅号';
        }
        if (param == "service") {
            return '服务号';
        }
        if (param == "enterprise") {
            return '企业号'
        }
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
angular.module('app').filter('StatusFilter', function () {
    return function (param) {
        if (param == "enabled") {
            return '可用';
        }
        if (param == "disabled") {
            return '不可用';
        }
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

angular.module('app').filter('GroupingTypeFilter', function () {
    return function (param) {
        if (param == "all") {
            return '全部分组';
        }
        if (param == "static") {
            return '静态分组';
        }
        if (param == "dynamic") {
            return '动态分组';
        }
    }
});

angular.module('app').filter('GroupingTypeFilter', function () {
    return function (param) {
        if (param == "all") {
            return '全部分组';
        }
        if (param == "static") {
            return '静态分组';
        }
        if (param == "dynamic") {
            return '动态分组';
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

angular.module('app').filter('expDate', function ($filter) {
    return function (data) {
        var sub = new Date(data);
        sub.setMonth(sub.getMonth() + 2);
        return $filter('date')(sub, 'yyyy-MM-dd');
    }
});