package pageFactories.unitTests.AdminSimulationPagesTests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewNodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteNodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditNodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeTypeDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeTypePageFactory;
import pageFactories.unitTests.TestBase;

public class NodeTypePageFactoryTest extends TestBase {
	
	@Test
	public void execute() {
		String userName = "DHASuperUser@mail.mil";
		
		LoginMod login = new LoginMod();
		login.execute(userName);
		
		HomePageFactory hp = new HomePageFactory();
		hp.clickNodeTypeInAdmin();
		
		NodeTypePageFactory nodeIndex = new NodeTypePageFactory();
		nodeIndex.setSearchBy("Decision");
		
		nodeIndex.clickCreateNewNodeType();
		
		CreateNewNodeTypePageFactory newNode = new CreateNewNodeTypePageFactory();
		
		//Error first
		newNode.setNodeType("Decision");
		newNode.clickCreate();
		assertEquals(newNode.readNodeTypeErrorMessage(), "Node Type already exists.", "Node Type Assertion");
		
		
		
		newNode.setNodeType("TESTING");
		newNode.setActiveCheckbox(false);
		newNode.setActiveCheckbox(true);
		newNode.clickCreate();
		
		nodeIndex = new NodeTypePageFactory();
		System.out.println(nodeIndex.getNodeTypeTable().isRowInTable("TESTING"));
		
		nodeIndex.getNodeTypeTable().clickLinkInRow("TESTING", "Edit");
		
		EditNodeTypePageFactory editNode = new EditNodeTypePageFactory();
		editNode.setNodeType("TESTING");
		editNode.setActiveCheckbox(false);
		editNode.clickSave();
		
		nodeIndex = new NodeTypePageFactory();
		nodeIndex.getNodeTypeTable().clickLinkInRow("TESTING", "Details");
		
		NodeTypeDetailsPageFactory nodeDetails = new NodeTypeDetailsPageFactory();
		System.out.println(nodeDetails.readNodeType());
		System.out.println(nodeDetails.isActiveChecked());
		nodeDetails.clickBackToList();
		
		nodeIndex = new NodeTypePageFactory();
		nodeIndex.getNodeTypeTable().clickLinkInRow("TESTING", "Delete");
		
		DeleteNodeTypePageFactory deleteNode = new DeleteNodeTypePageFactory();
		System.out.println(deleteNode.readNodeType());
		System.out.println(deleteNode.isActiveChecked());
		deleteNode.clickDelete();
		
		

		
		
		
		
		
		
		
		
		
		
		
		
	}

}
