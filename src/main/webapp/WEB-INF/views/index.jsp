<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp" ng-controller="myCtrl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book App</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
 <script>
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	
   loadall();
   
   // Load all function to get all books through ajax call
 function loadall(){
	  var urlall = "http://localhost:8082/BookApp/allbooks";
  

   $http.get(urlall).then( function(response) {
      $scope.booklist = response.data;
     
   });
}

   //remove function to delete book from the datastore
   
   $scope.remove = function(id) {
	    
	    var urlremove = "http://localhost:8082/BookApp/remove/"+id;
	   
	    	   $http.get(urlremove).then( function(response) {
	    		   loadall();
	    	      
	    	   }, function error(response) {
	    		   alert(response.data);
	    	   });
	}
   
   //edit function to edit book details through ajax call
   $scope.edit = function(id) {
	  
	   var urledit = "http://localhost:8082/BookApp/edit/"+id;
	  
	   $http.get(urledit).then( function(response) {
		  
		   $scope.bookdata = response.data;
		   
		  
		   $scope.name = $scope.bookdata[0].id;
	   }, function error(response) {
		  
	   });
	}
   
});
</script>
</head>
<body>
     

 <h2>Add Book Data</h2>
 
  <form:form method="POST" action="savebook" >
      <table>
     	 <tr>
           <td><form:label path="id">Employee ID:</form:label></td>
           <td><form:input path="id" value="{{bookdata[0].id}}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="name">Book Name:</form:label></td>
           <td><form:input path="name" value="{{bookdata[0].name}}"/></td>
       </tr>
       <tr>
           <td><form:label path="isbn">Book ISBN:</form:label></td>
           <td><form:input path="isbn" value="{{bookdata[0].isbn}}" /></td>
       </tr>
       <tr>
           <td><form:label path="author">Book Author:</form:label></td>
           <td><form:input path="author" value="{{bookdata[0].author}}"/></td>
       </tr>
       
       <tr>
           <td><form:label path="publisher">Book Publisher:</form:label></td>
                    <td><form:input path="publisher" value="{{bookdata[0].publisher}}" /></td>
       </tr>
          <tr>
         <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
   </table> 
  </form:form>
 
<h1>Book List</h1>

<table border="1px">
    <tr>
         <th>BOOK ID</th>
        <th>BOOK Name</th>
        <th>BOOK ISBN</th>
        <th>BOOK AUTHOR</th>
        <th>BOOK PUBLISHER</th>
        
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
      <tr ng-repeat="book in booklist">
        	 <td>{{book.id}}</td>
            <td>{{book.name}}</td>
            <td>{{book.isbn}}</td>
            <td>{{book.author}}</td>
            <td>{{book.publisher}}</td>
             <td><button type="button" ng-click="edit(book.id)">Edit</button></td>
            <td><button type="button" ng-click="remove(book.id)">Remove</button></td>
           
          
   		 </tr>
</table>
  </body>
</html>
