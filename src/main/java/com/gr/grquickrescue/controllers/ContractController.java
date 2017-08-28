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
	@PostConstruct
	public void init() 
	{
		
		contractService = (ContractServiceRemote)ServiceManager.getInstance(ContractServiceRemote.class.getName());
		accountService = (AccountServiceRemote)ServiceManager.getInstance(AccountServiceRemote.class.getName());
	}
	public String openAccountContracts(int accountId) 
	{
		this.setAccount(accountService.findAccountById(accountId) );
		updateContractsList();
		return "/resources/secured/contract/contract";
	}
	public void updateContractsList() 
	{
		setContractsList(contractService.findContractsByAccountId(this.getAccount().getId()));
	}
	public void addContract() 
	{
		Contract newContract = new Contract();
		newContract.setStartDate(this.getStartDate().toString());
		newContract.setEndDate(this.getEndDate().toString());
		newContract.setMaxContacts(this.getMaxContacts());
		newContract.setMaxLogins(this.getMaxLogins());
		newContract.setAccount(this.getAccount());
		contractService.saveContract(newContract);
		updateContractsList();
	}
	public String updateContract(Contract contract) 
	{
		contract.setEditable(true);
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
}
