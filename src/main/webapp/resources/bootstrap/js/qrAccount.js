$(document).ready(function() 
{
	$("#addNewAccountButton").click(function(){
		$("#addNewAccountDiv").slideDown();
	})
	$("#cancelAddingNewAccountButton").on('click',function(){
		$("#addNewAccountDiv").slideUp();
	})
});

function closediv() {
	$("#addNewAccountDiv").slideUp();
}