package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewInitialPhysiologicParametersTemplatePageFactory;
import pageFactories.Admin.AdminSimulationPages.InitialPhysiologicParametersTemplatePageFactory;
import pageFactories.unitTests.TestBase;

public class InitialPhysiologicParametersTemplatePageFactoryTest extends TestBase{
	
	@Test
	
	public void test() {
		LoginMod login = new LoginMod();
		
		login.execute("jesse_DHA_super_user@mail.mil");
		
		//Home Page
		HomePageFactory home = new HomePageFactory();
		home.clickInitialPhysiologicParametersTemplateInAdmin();
		
		InitialPhysiologicParametersTemplatePageFactory ipp = new InitialPhysiologicParametersTemplatePageFactory();
		ipp.clickCreateInitialPhysiologicParametersTemplate();
		
		CreateNewInitialPhysiologicParametersTemplatePageFactory cipp = new CreateNewInitialPhysiologicParametersTemplatePageFactory();
		
		cipp.clickCreate();
		System.out.println("Error 1: " + cipp.readNameErrorMessage());
		System.out.println("Error 1: " + cipp.readDescriptionErrorMessage());
		
		String longTextTemplate = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea";
		String longTextDescription = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.Sed ut";
		
		cipp.setTemplateName(longTextTemplate);
		cipp.setDescription(longTextDescription);
		
		System.out.println("Error 1: " + cipp.readNameErrorMessage());
		System.out.println("Error 1: " + cipp.readDescriptionErrorMessage());
		
		cipp.clickBackToInitialPhysiologicParametersTemplateListLink();
		
		
		
		
		
	}

}
