package pageFactories.Admin.AdminSimulationPages;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.unitTests.TestBase;

public class NodeConnectorPageFactoryTest extends TestBase{
	
	@Test
	public void execute() {
		String userName = "DHASuperUser@mail.mil";
		
		LoginMod login = new LoginMod();
		login.execute(userName);
		
		HomePageFactory hp = new HomePageFactory();
		hp.clickNodeConnectorInAdmin();
		
		NodeConnectorPageFactory nodeIndex = new NodeConnectorPageFactory();
		nodeIndex.setSearchBy("Curved");
		
		nodeIndex.clickCreateNewNodeConnector();
		
		CreateNewNodeConnectorPageFactory newNode = new CreateNewNodeConnectorPageFactory();
		
		//Error first
		newNode.setNodeConnector("Curved");
		newNode.clickCreate();
		assertEquals(newNode.readErrorText(), "Connector Type already exists.", "Connector Type Assertion");
		
		
		newNode.setNodeConnector("Node_Connector_Test");
		newNode.setActiveCheckbox(false);
		newNode.setActiveCheckbox(true);
		newNode.clickCreate();
		
		nodeIndex = new NodeConnectorPageFactory();
		System.out.println(nodeIndex.getNodeConnectorTable().isRowInTable("Node_Connector_Test"));
		
		nodeIndex.getNodeConnectorTable().clickLinkInRow("Node_Connector_Test", "Edit");
		
		EditNodeConnectorPageFactory editNode = new EditNodeConnectorPageFactory();
		editNode.setNodeConnector("Node_Connector_Test");
		editNode.setActiveCheckbox(false);
		editNode.clickSave();
		
		nodeIndex = new NodeConnectorPageFactory();
		nodeIndex.getNodeConnectorTable().clickLinkInRow("Node_Connector_Test", "Details");
		
		NodeConnectorDetailsPageFactory nodeDetails = new NodeConnectorDetailsPageFactory();
		System.out.println(nodeDetails.readNodeConnector());
		System.out.println(nodeDetails.isActiveChecked());
		nodeDetails.clickBackToList();
		
		nodeIndex = new NodeConnectorPageFactory();
		nodeIndex.getNodeConnectorTable().clickLinkInRow("Node_Connector_Test", "Delete");
		
		DeleteNodeConnectorPageFactory deleteNode = new DeleteNodeConnectorPageFactory();
		System.out.println(deleteNode.readNodeConnector());
		System.out.println(deleteNode.isActiveChecked());
		deleteNode.clickDelete();
	}
}
