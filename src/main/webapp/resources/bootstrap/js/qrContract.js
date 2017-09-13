$(document).ready(function(){
	$("#cancelAddingNewContractButton").on('click',function(){
		$("#addNewContractDiv").slideUp();
		$("#addAccountForm\\:maxcontacts").val("");
		$("#addAccountForm\\:maxlogins").val("");
		$("#addAccountForm\\:startdate").val("");
		$("#addAccountForm\\:enddate").val("");
	});
	$("#addAccountForm\\:startdate").datepicker({
        numberOfMonths: 1,
        minDate: new Date(),
        onSelect: function(selected) {
          $("#addAccountForm\\:enddate").datepicker("option","minDate", selected)
        }
    });
    $("#addAccountForm\\:enddate").datepicker({ 
        numberOfMonths: 1,
        onSelect: function(selected) {
           $("#addAccountForm\\:startdate").datepicker("option","maxDate", selected)
        }
    });
});