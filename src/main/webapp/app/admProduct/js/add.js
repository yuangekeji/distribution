angular.module('admProduct').controller('admProductAddCtrl',function ($q, title, $scope, $http,  $state, Notify, $sessionStorage) {
    title.setTitle('添加商品');
    $scope.goods = {
        imgeUrl:""
    };
    $scope.submitFlag = true;
    $scope.goodsTypes = [];

    $scope.initParams = function () {
        $http.get(ctx + "/admGoods/findGoodsTypes").success(function (resp) {
            if(resp.successful){
                $scope.goodsTypes = resp.data;
            }
        }).error(function (resp) {
            console.log(resp);
        });
    };

    $scope.initParams();

    $scope.add = function () {
        if($scope.submitFlag) {
            $scope.submitFlag = false;
            if($scope.check()){
                $scope.startLoading();
                $http.post(ctx + "/admGoods/insert", $scope.goods).success(function (resp) {
                    if(resp.successful){
                        $scope.stopLoading();
                        $state.go("app.admProduct");
                    }else{
                        $scope.stopLoading();
                        console.log(resp);
                    }
                }).error(function (resp) {
                    $scope.stopLoading();
                    console.log(resp);
                })
            }
        }
    };

    $scope.check = function () {
          if(!$scope.goods.goodsName||!$scope.goods.goodsName.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品名称。");
          }else if(!$scope.goods.goodsPrice||!$scope.goods.goodsPrice.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品价格。");
          }else if(!/^\+?[1-9][0-9]*$/.test($scope.goods.goodsPrice.trim())){
              $scope.submitFlag = true;
              Notify.warning("商品价格只能为正整数，请重新输入。");
          }else if(!$scope.goods.goodsNum||!$scope.goods.goodsNum.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品库存。");
          }else if(!/^\+?[1-9][0-9]*$/.test($scope.goods.goodsNum.trim())){
              $scope.submitFlag = true;
              Notify.warning("商品库存只能为正整数，请重新输入。");
          }else if(!$scope.goods.goodsType){
              $scope.submitFlag = true;
              Notify.warning("请选择商品类型。");
          }else if(!$scope.goods.info||!$scope.goods.info.trim()){
              $scope.submitFlag = true;
              Notify.warning("请输入商品介绍。");
          }else{
              return true;
          }
    };

    /**loading*/
    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    };

    $scope.stopLoading=function () {
        App.unblockUI(e1);
    };

    /**图片上传start*/
    $scope.reader = new FileReader();   //创建一个FileReader接口
    $scope.form = {     //用于绑定提交内容，图片或其他数据
        image:{},
    };
    $scope.thumb = {};      //用于存放图片的base64
    $scope.thumb_default = {    //用于循环默认的‘加号’添加图片的框
        1111:{}
    };

    $scope.img_upload = function(files) {       //单次提交图片的函数
        $scope.guid = (new Date()).valueOf();   //通过时间戳创建一个随机数，作为键名使用
        $scope.reader.readAsDataURL(files[0]);  //FileReader的方法，把图片转成base64
        $scope.reader.onload = function(ev) {
            $scope.$apply(function(){
                $scope.thumb[$scope.guid] = {
                    imgSrc : ev.target.result,  //接收base64
                }
            });
        };

        var data = new FormData();      //以下为像后台提交图片数据
        data.append('image', files[0]);
        data.append('guid',$scope.guid);
        $http({
            method: 'post',
            url: ctx + '/admGoods/uploadImage',
            data:data,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function(resp) {
            if (resp.successful) {
                $scope.goods.imgeUrl = resp.data;
                console.log($scope.form)
            }else{
                Notify.warning(resp.data);
            }
        }).error(function (resp) {
            console.log(resp);
        })
    };

    $scope.img_del = function(key) {    //删除，删除的时候thumb和form里面的图片数据都要删除，避免提交不必要的
        var guidArr = [];
        for(var p in $scope.thumb){
            guidArr.push(p);
        }
        delete $scope.thumb[guidArr[key]];
        delete $scope.form.image[guidArr[key]];
    };
    /**图片上传end*/
});
