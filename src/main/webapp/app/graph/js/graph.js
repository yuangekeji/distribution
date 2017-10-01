angular.module('graph').controller('graphCtrl',
    function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,ConfirmModal,settings ,$uibModal, $log) {
        title.setTitle('推荐网络图');
        var e1 = $('.portlet');

        $scope.collapseAll = function () {
            $("#tree_2").jstree(true).close_all();

        };

        $scope.expandAll = function () {
            $("#tree_2").jstree(true).open_all();
        };

        $scope.data=[];

        $scope.getGraphTree = function () {
            App.blockUI({
                target: e1,
                animate: true,
                overlayColor: 'none'

            });
            $http.post(ctx + "/node/tree").success(function (resp) {
                if(resp.successful){
                    // console.info(JSON.stringify(resp.data));
                    $scope.handleSample2({nodeId:0,
                        flag:null,
                        id:'0',
                        text:"总公司",
                        state:{checkbox_disabled:true},
                        children:[resp.data]
                    });

                }
                App.unblockUI(e1);
            }).error(function (resp) {
                console.log(resp);
                App.unblockUI(e1);
            });

        }

        $scope.getGraphTree();

        $scope.handleSample2 = function (data) {

            // console.info(data);

            $('#tree_2').jstree({
                'plugins': ["checkbox"],
                'core': {
                    "multiple": false,//单选
                    "themes" : {
                        "responsive": false
                    },
                    'data': [
                        data
                    ]
                },
                checkbox: {
                    three_state: false,
                    cascade: "none"
                },
                "types" : {
                    "default" : {
                        "icon" : "fa fa-folder icon-state-warning icon-lg"
                    },
                    "file" : {
                        "icon" : "fa fa-file icon-state-warning icon-lg"
                    }
                }
    }).bind("loaded.jstree",function(e,data){
               var id = "tree_2";
               jQuery("#"+id).jstree("open_all");
                $('li[data-checkstate="checked"]').each(function () {
                    $(this).addClass('jstree-checked');
                });
                $('li[data-checkstate="undetermined"]').each(function () {
                    $(this).addClass('jstree-undetermined');
                });
    });
}

    $('#tree_2').on("changed.jstree", function (e, data) {

            if(data.node.state.selected){
                // ConfirmModal.show({
                //     text: '是否要给 '+data.node.text+' 添加新的节点？',
                //     isCancel:true //false alert ,true confirm
                // }).then(function (sure) {
                //     if (!sure) {
                //         return;
                //     }
                //     $state.go('app.recommendAdd',{mobile:data.node.id});
                // });
            }
       });

    });