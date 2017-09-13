package com.gr.grquickrescue.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.gr.grquickrescue.models.Contract;
import com.gr.grquickrescue.services.AccountServiceRemote;
import com.gr.grquickrescue.services.ContractServiceRemote;
import com.gr.grquickrescue.services.ServiceManager;
import com.gr.grquickrescue.utils.HttpSessionUtility;
@ManagedBean
@SessionScoped
public class ContractController extends Contract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ContractServiceRemote contractService;
	@EJB
	private AccountServiceRemote accountService;
	private List<Contract> contractsList;
	private boolean renderDiv;
	private boolean renderError;
	private String errorMessage;
	private boolean contractExist;
	private boolean editing;
	@PostConstruct
	public void init() 
	{
		renderDiv = false;
		renderError = false;
		editing = false;
		contractService = (ContractServiceRemote)ServiceManager.getInstance(ContractServiceRemote.class.getName());
		accountService = (AccountServiceRemote)ServiceManager.getInstance(AccountServiceRemote.class.getName());
		this.setAccount(accountService.findAccountById((Integer)(HttpSessionUtility.getSession().getAttribute("accountId"))));
		updateContractsList();
	}
	public String openAccountContracts(int accountId) 
	{
		this.setAccount(accountService.findAccountById(accountId) );
		updateContractsList();
		
		return "/resources/secured/contract/contract";
	}
	public void updateContractsList() 
	{
		contractExist = false;
		closeNewContractDiv();
		setContractsList(contractService.findContractsByAccountId(this.getAccount().getId()));
		for (Contract contract : contractsList) {
			if(contract.isActive()) {
				setContractExist(true);
				break;
			}
		}
	}
	public String openNewContractDiv() 
	{
		errorMessage = "You can not add more contracts !";
		renderDiv = true;
		renderError = false;
		if(editing) {
			renderError = false;
		}else {
			if(contractExist) {
				renderError = true;
			}else {
				renderError = false;
			}
		}
		return null;
	}
	public String closeNewContractDiv() {
		renderDiv = false;
		renderError = false;
		editing = false;
		emptyDiv();
		return null;
	}
	public void addContract() 
	{
		if(editing) {
			Contract contract = new Contract( this.getMaxContacts(),this.getMaxLogins(),this.getStartDate(), this.getEndDate(),this.getAccount());
			contract.setId(this.getId());
			contractService.updateContract(contract);
			editing = false;
		}else {
			if(!contractExist) {
				Contract newContract = new Contract();
				newContract.setStartDate(this.getStartDate());
				newContract.setEndDate(this.getEndDate());
				newContract.setMaxContacts(this.getMaxContacts());
				newContract.setMaxLogins(this.getMaxLogins());
				newContract.setAccount(this.getAccount());
				contractService.saveContract(newContract);
				this.renderDiv = false;
				
			}
		}
		updateContractsList();
	}
	private void emptyDiv() {
		this.setStartDate("");
		this.setEndDate("");
		this.setMaxContacts(0);
		this.setMaxLogins(0);
	}
	public String updateContract(Contract contract) 
	{
		//updateContractsList();
		this.editing = true;
		this.setMaxContacts(contract.getMaxContacts());
		this.setMaxLogins(contract.getMaxLogins());
		this.setStartDate(contract.getStartDate());
		this.setEndDate(contract.getEndDate());
		this.setId(contract.getId());
		openNewContractDiv();
		return null;
	}
	public void deleteContract(Contract contract) 
	{
		contractService.deleteContract(contract.getId());
		updateContractsList();
	}
	public String saveEdit(Contract contract1) 
	{
		for (Contract contract : getContractsList()) {
			contract.setEditable(false);
		}
		contractService.updateContract(contract1);
		updateContractsList();
		return null;
	}
	public void cancelEdit(Contract contract) 
	{
		contract.setEditable(false);
	}
	public List<Contract> getContractsList() {
		return contractsList;
	}
	public void setContractsList(List<Contract> contractsList) {
		this.contractsList = contractsList;
	}
	
	public boolean isRenderDiv() {
		return renderDiv;
	}
	public void setRenderDiv(boolean renderDiv) {
		this.renderDiv = renderDiv;
	}
	public boolean isRenderError() {
		return renderError;
	}
	public void setRenderError(boolean renderError) {
		this.renderError = renderError;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isContractExist() {
		return contractExist;
	}
	public void setContractExist(boolean contractExist) {
		this.contractExist = contractExist;
	}
}
