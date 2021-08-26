<table class="editInfo">
	<tr>
		<td>
			<div>
				<div>Id:</div>
				<div>
					<input type="text" name="index" value="${empInfo.index}" />
				</div>
			</div>
		</td>
		<td>
			<div>

				<div>Name:</div>
				<div>
					<input type="text" name="name" value="${empInfo.name}" />
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td>

			<div>

				<div>date_of_birth:</div>
				<div>
					<input type="date" name="date_of_birth"
						value="${empInfo.dateOfBirth}" />
				</div>
			</div>
		</td>
		<td>
			<div>
				<div>Address:</div>
				<div>
					<input type="text" name="address" value="${empInfo.address}" />
				</div>

			</div>
			<div>
				<div>Id Dep:</div>
				<div>
					<%-- <input type="text" name="idDep" value="${empInfo.idDep}" /> --%>
					<select id= "dep" name="idDep">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select>
					<script type="text/javascript">
						window.onload = function() {
						    var depId =${empInfo.idDep};
						    var options= document.getElementById('dep').options;
						    for (var i= 0, n= options.length; i < n ; i++) {
						        if (options[i].value==depId) {
						            document.getElementById("dep").selectedIndex = i;
						            break;
						        }
						    }
						}
					</script>
				</div>
			</div>
		</td>
	</tr>
</table>
<div>
<br/>
	<div>
		<input type="submit" value="Save" /> 
	</div>
</div>
