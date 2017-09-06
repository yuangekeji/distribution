angular.module('graph').controller('graphCtrl',
    function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,ConfirmModal,settings ,$uibModal, $log) {

        var e1 = $('.portlet');

        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'

        });

        window.setTimeout(function() {
            App.unblockUI(e1);
        }, 1000);


        $scope.remove = function (scope) {
            scope.remove();
        };

        $scope.toggle_ = function (scope) {
            scope.toggle_();
        };

        $scope.moveLastToTheBeginning = function () {
            var a = $scope.data.pop();
            $scope.data.splice(0, 0, a);
        };

        $scope.newSubItem = function (scope) {

            var nodeData = scope.$modelValue;
            nodeData.nodes.push({
                id: nodeData.id * 10 + nodeData.nodes.length,
                title: nodeData.title + '.' + (nodeData.nodes.length + 1),
                nodes: []
            });
        };

        $scope.collapseAll = function () {
            $scope.$broadcast('angular-ui-tree:collapse-all');
        };

        $scope.expandAll = function () {
            $scope.$broadcast('angular-ui-tree:expand-all');
        };

        $scope.data=[];

        $scope.getGraphTree = function () {

            $http.post(ctx + "/node/tree").success(function (resp) {
                if(resp.successful){
                    console.info(resp);
                    $scope.data[0] = resp.data;
                }
            }).error(function (resp) {
                console.log(resp);
            });

        }

        $scope.getGraphTree();
        //
        // $scope.data = [{
        //     'id': 1,
        //     'title': 'node1',
        //     'nodes': [
        //         {
        //             'id': 11,
        //             'title': 'node1.1',
        //             'nodes': [
        //                 {
        //                     'id': 111,
        //                     'title': 'node1.1.1',
        //                     'nodes': []
        //                 }
        //             ]
        //         },
        //         {
        //             'id': 12,
        //             'title': 'node1.2',
        //             'nodes': []
        //         }
        //     ]
        //   }, {
        //     'id': 2,
        //     'title': 'node2',
        //     'nodes': [
        //         {
        //             'id': 21,
        //             'title': 'node2.1',
        //             'nodes': []
        //         },
        //         {
        //             'id': 22,
        //             'title': 'node2.2',
        //             'nodes': []
        //         }
        //     ]
        // }];

    });