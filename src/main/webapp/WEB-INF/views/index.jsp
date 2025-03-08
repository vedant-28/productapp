<html>
	<head>
		<%@ include file="./base.jsp" %>
		<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
	</head>
	<body>
		<div class="container mt-3">
			<div class="row">
				<div class="col-md-12">
					<h1 class="text-center mb-3">Welcome to Product APP</h1>
					<table class="table table-striped">
  						<thead>
    						<tr>
      							<th scope="col">ID</th>
      							<th scope="col">Product Name</th>
      							<th scope="col">Description</th>
      							<th scope="col">Price</th>
      							<th scope="col">Action</th>
    						</tr>
  						</thead>
  						<tbody>
  							<c:forEach items="${products }" var="p">
    							<tr>
      								<th scope="row">PROD${p.productId }</th>
      								<td>${p.name }</td>
      								<td>${p.description }</td>
      								<td class="font-weight-bold">&#8377; ${p.price }</td>
      								<td class="py-2">
      									<a href="delete-product/${p.productId }">
      										<i class="fa-solid fa-trash text-danger"></i>
      									</a>
      									<a href="update-product/${p.productId }">
      										<i class="fa-solid fa-pen-nib text-primary"></i>
      									</a>
      								</td>
    							</tr>
    						</c:forEach>
  						</tbody>
					</table>
					
					<div class="container text-center">
						<a href="add-product" class="btn btn-outline-success">Add Product</a>
					</div>
					
				</div>
			</div>
		</div>
	</body>
</html>