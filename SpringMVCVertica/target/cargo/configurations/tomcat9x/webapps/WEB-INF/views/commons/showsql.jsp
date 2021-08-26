
<div class="frame">
	<div class='title'>Query:</div>
	<c:forEach var="queryPost" items="${queryPosts}">
		<p>${queryPost}</p>
		<p>------------------------</p>
	</c:forEach>
	<c:forEach var="queryGet" items="${queryGets}">
		<p>${queryGet}</p>
		<p>------------------------</p>
	</c:forEach>
</div>
