angular.module("book",[]).controller("Book", function($scope, $http){
	$scope.selectedBook="hhh";
	$http.get("rest/book").then(function(response){
		$scope.books = response.data;
	});
	
	$scope.addBook = function(){
		$http.post("rest/book", $scope.book).then(function(response){
			$scope.books.push(response.data)
		});
	}
	$scope.selectBook = function(){
		$scope.book =  angular.fromJson(angular.toJson($scope.books[$scope.selected]))
	}
	$scope.reset = function(){
		$scope.book = new Object();
		$scope.selected = null;
	}
	$scope.updateBook = function(){
		$scope.updateModel = new Object();
		$scope.updateModel.oldBook = $scope.books[$scope.selected];
		$scope.updateModel.newBook = angular.fromJson($scope.book);
		$http.put("rest/book", $scope.updateModel).then(function(response){
			 $scope.books[$scope.selected] = $scope.updateModel.newBook;
		});
	}
	$scope.deleteBook = function(){
		$http({ url: "rest/book", 
            method: 'DELETE', 
            data: $scope.book,
            headers: {"Content-Type": "application/json;charset=utf-8"}
		}).then(function(response){
			var newBooks = new Array();
			for(var i in $scope.books){
				if(i!=$scope.selected){
					newBooks.push($scope.books[i]);
				}
			}
			$scope.books = newBooks;
		});
	}
});