# Description #
[Thymeleaf](http://www.thymeleaf.org/) is a java template engine that support for natural templating. Natural templating means that web designer can design the web application pages using HTML, CSS and JS naturally without need to know about the back end at all. So the designer can can focus in developing UI and UX using front end technology (HTML, CSS and JS).

# Study Case #
Create a simple employee application that consist only 2 page, first page will be a home page and the second page will contain employee list and also contain ajax interaction.

# What to do: #

## Code structure ##
The thymeleaf template in spring code structure for this case is defined like this:  
	\src\main\resources  
		- static  
		|---- css  
		|  |---- self-defined.css  
		|  
		|---- js  
		|  |---- self-defined.js  
		|  
		- templates  
		|---- natural  
		|  |---- index.html  
		|  |---- list-page.html  
		|  
		|---- index.html  
		|---- list-page.html  
			
The main focus of this guide is the natural templating with thymeleaf in Spring. So the discussion of how to set up thymeleaf in spring won't be covered in this guide, refer to [this post](https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-thymeleaf/) if you need.

## Front end part ##
The front end developer (or web designer) should develop the application UI and UX in form of HTML, CSS and JS. And the location for the HTML files should be placed under `\src\main\resources\templates\natural`, location for resource (CSS and JS) should be place in `\src\main\resources\static\css` and `\src\main\resources\static\js` accordingly. 

The designer can develop the UI and UX naturally using web front end technology, for example the first page (`index.html`) will be plain HTML file with needed CSS and JS file, like this:  
[index.html](https://github.com/erikfarhanmalik/thymeleaf-natural-templating/blob/fisrt-stage/src/main/resources/templates/natural/index.html)  
[list-page.html](https://github.com/erikfarhanmalik/thymeleaf-natural-templating/blob/master/src/main/resources/templates/natural/list-page.html)  

Both file are plain HTML with it's needed css and js. The designer can open those file by `right click and open with a browser`.

### Keep in mind for front end part ###
* For navigation purpose, use relative path like what you saw in [index.html](https://github.com/erikfarhanmalik/thymeleaf-natural-templating/blob/fisrt-stage/src/main/resources/templates/natural/index.html)

```html
<a class="waves-effect waves-light btn-large" href="./list-page.html">
	Go to List Page
	<i class="material-icons right">send</i>
</a>
```
 
* Use relative path for self defined resources, ex:

```html
<link rel="stylesheet" href="../../static/css/self-defined.css" />
<script type="text/javascript" src="../../static/js/self-defined.js" />
```

* Use cloud repositories for vendor provided resources, ex:

```html
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.js"></script>
```

* Make a consistent layout, for example, header, footer, and side bar should be placed in consistent manner. It will help in integration process with the backend. 

* For features that need ajax request, like this code in [list-page.html](https://github.com/erikfarhanmalik/thymeleaf-natural-templating/blob/master/src/main/resources/templates/natural/list-page.html):  

```javascript
<script type="text/javascript">
	function doSomeAjaxCall() {
			$.ajax({method : "GET",
					url : "http://localhost:9000/api/employee"})
			.done(function(response) {
					$("#ajax-response").html(JSON.stringify(response));
				});
	}
</script>
```
you should create a mock server that can handle `http://localhost:9000/api/employee` and define the needed response for that request, the defined responses will become a contract between front-end and back-end for interchanging message api. You can use tool like [this project](https://github.com/erikfarhanmalik/rest-api-dummy).  
It's better to put the json file response for the dummy api inside source code, because that response will be the contract between the front-end and back-end it's better to manage those json in the same repository for easy reference for both font-end and back-end developer. In this guide, the location is in `\src\main\resources\templates\natural\ajax-contract`


## Back end part ##
For back-end part, first thing to do is create the thymeleaf template under `\src\main\resources\templates` according to the natural template pages.  
So, you should create index.html and list-page.html under `\src\main\resources\templates` these two files will be the thymelaf engine version of the natural template:
- templates  
		|---- natural  
		|  |---- index.html  
		|  |---- list-page.html  
		|  
		|---- index.html  
		|---- list-page.html 

on the thymelaf engine version of the natural template put some html element only to make sure that the data is loaded properly if the natural version still in progress, and do it directly on the natural pages if the natural pages already done.
		
Then, you should add the needed thymeleaf specific attribute tag into every natural pages, for example in [index.html](https://github.com/erikfarhanmalik/thymeleaf-natural-templating/blob/master/src/main/resources/templates/natural/index.html).  
- Add `<html xmlns:th="http://www.thymeleaf.org">` for thymeleaf name space
- Modify the `<span>` for name by add th:text (`<span th:text="${user.name}">Friski Y Pratama</span>`) so that the span value will take the value from server instead from the template when it's rendered using template engine, and do similar thing to other sections.
- Modify all link by add like by add th:href like `<a class="waves-effect waves-light btn-large" href="./list-page.html" th:href="@{/list-page}">` so that the href target value will take the value from server instead from the template when it's rendered using template engine.
- Bundle needed resource (CSS and JS) using dandelion
- Add th:fragment="inline-script-container" (`<script type="text/javascript" th:fragment="inline-script-container">`) for every in line java script.
- Add `th:fragment` for element that will be included in real thymeleaf template, for example:  

```html
<div class="navbar-fixed" th:fragment="navigation-bar">
<div class="content" th:fragment="content">
```

- In the real template place the elements from the natural template, and arrange those elements like in [index.html](https://github.com/erikfarhanmalik/thymeleaf-natural-templating/blob/master/src/main/resources/templates/index.html) template.

