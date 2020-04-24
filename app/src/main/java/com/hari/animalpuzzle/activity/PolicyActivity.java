package com.hari.animalpuzzle.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import com.hari.animalpuzzle.R;

public class PolicyActivity extends Activity {

	TextView policyView, introductionView, rockinView, aboutChildrenView, aboutChildrenDeclrationView, informationCollectView, informationCollectDeclrationView, informationUses,
	         informationDetailView, disclosureofApp, disloserDetail, thirdPartyView, thirdPartyDetailView, yourRights, yourRightsdetail, datasecurity, datasecuritydetailView,
	         changePolicy, changePolicyDetails, contact, contactDetail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_policy);
		String policy = "This privacy policy applies to  our website at www.rockin.co.in and  any of our games, products or services (whether accessible via a web application,  third party platform, social networking service or otherwise).By using any of our Services, you confirm that you have read,understood and agree to this privacy policy in its entirety. If you do not agree to this policy, please do not use any of the Services or Products by Rockin Entertainment Service.If you have any queries, please email us at info@rockin.co.in" +
				"\n " ;
//				"\n Introduction" +
//				"\n 1.    Rockin Entertainment Service,  ('we' or 'us' or 'our', depending on what makes the most sense) are committed to protecting and respecting your privacy." +
//				"\n 2.    This privacy policy sets out the basis on which we will process any personal information we collect from you, or that you provide to us. Please read this privacy policy carefully so that you understand how we will treat your personal information." +
//				"\n A quick word about children" +
//				"\n 3. Rockin Entertainment Service recognize we have a special obligation to protect personal information we obtain from children. We do not and will not knowingly collect information from any unsupervised child under the age of 13. In certain jurisdictions, prior written permission or other verifiable consent may also be required from a parent or guardian for children under the age of 18." +
//				"\n Information we may collect from you" +
//				"\n 4. We respect your right to privacy and will only process personal information you provide to us in accordance with applicable privacy laws, such as the Privacy and Electronic Communications (EC Directive) Regulations  within the In." +
//				"\n 5. The kinds of data which we may process in order to monitor our Services and improve your user experience include: " +
//				"\n - Your name (first and last name). " +
//				"\n - Your sex." +
//				"\n - Your contact details (such as your email address and/or mobile phone number)." +
//				"\n - Your birth date. " +
//				"\n - Your screen name and profile picture." +
//				"\n - Technical or other details about any device which you use to access the Services: Internet and/or network connection (including your IP address, Media Access Control (MAC); operating system, browser type or other software; hardware (including computer hardware or mobile model); mobile device details (including your mobile device type and number; mobile carrier details; and unique mobile device ID) or other technical details." +
//				"\n - Details of your use of our Services including, but not limited to: traffic data, location/geographical data, the Service resources that you access, the time you spend on the Services, crash reports and reasons for connection drops." +
//				"\n 6. We may collect and process data about you through the following means: Data that you provide to us via Facebook or which you make publicly available on Facebook, or which you give us via any forms on our Site or by contacting us. Information provided when you report a problem with any of the Services. We may also ask you to complete surveys that we use for research purposes, although you do not have to respond to them." +
//				"\n 7. We may collect this data through our Services or third party services connected with them (including in particular Facebook and Google Analytics)" +
//				"\n 8. We store the data that we collect from you in our servers in the India. However, this data may be transferred to, and stored at, a location outside of the  . It may also be processed by staff  who work for us or for one of our suppliers. Such staff may be engaged in, among other things, the performance of our obligations from any contracts entered into with you and the provision of support services. By submitting your personal data, you agree to this transfer, storing or processing. We will take all steps reasonably necessary to ensure that your data is treated securely and in accordance with this privacy policy." +
//				"\n Uses made of your information " +
//				"\n 9. We use information held about you in the following ways: 9. We use information held about you in the following ways:" +
//				"\n - To ensure that content from our Services is presented in the most effective manner for you and for your computer or other device from which you access the Services." +
//				"\n - To provide you with information or services that you request from us or which we feel may interest you, if you have consented to be contacted for such purposes." +
//				"\n - To perform our obligations from any contracts entered into with you." +
//				"\n - To allow you to participate in interactive features of our Services, if you choose." +
//				"\n - To notify you about changes to our Services." +
//				"\n 10. Please remember that any communications you have via our Services, including for example Apps, may reveal your screen name, your profile picture and the content of your communications to other users." +
//				"\n 11. We are not responsible for the activities of other users or other third parties to whom you choose to provide your personal information (whether via our Services or otherwise)." +
//				"\n Disclosure of your information " +
//				"\n 12. We may disclose your personal information to any member of our group, which means our subsidiaries, our ultimate holding company and its subsidiaries." +
//				"\n 13. We may disclose your personal information to third parties in the following circumstances:" +
//				"\n - In the event that we sell or buy any business or assets, in which case we may disclose your personal information to the seller or buyer of such business or assets." +
//				"\n - If we or substantially all of our assets are acquired by a third party, in which case personal information held by us about our customers will be a transferred asset." +
//				"\n - If we are under a duty to disclose or share your personal information in order to comply with any legal obligation, or in order to enforce or apply, or to protect our rights, property, or safety of our customers, or others." +
//				"\n Third party sites " +
//				"\n 14. Our Services may, from time to time, contain links to and from the websites of our partner networks, advertisers and affiliates. These websites have their own privacy policies. Please note that if you follow a link to any of these websites, we do not accept any responsibility or liability for these policies, nor do we endorse such websites." +
//				"\n  Your rights" +
//				"\n 15. You have the right to ask us not to process your personal information for marketing purposes. We will usually inform you (before collecting your data) if we intend to use your data for such purposes. You can exercise this right by contacting us at info@rockin.co.in." +
//				"\n 16. You can email us at info@rockin.co.in to request that we delete your personal information from our database. We will use commercially reasonable efforts to honor your request. We may retain an archived copy of your records if required to do so by law or for legitimate business purposes." +
//				"\n Data security " +
//				"\n 17. Unfortunately, the transmission of information via the Internet is not completely secure. We will do our best to protect your personal information but we cannot guarantee the security of your data transmitted to our Services, any transmission is at your own risk. We will use strict procedures and security features to try to prevent unauthorized access to your information." +
//				"\n Changes to our privacy policy " +
//				"\n 18. Any changes we may make to our privacy policy in the future will be posted on this page and, where appropriate, notified to you by email." +
//				"\n Contact" +
//				"\n 19. Please address any questions or comments regarding this privacy policy to info@rockin.co.in.";
		        
		
		String rockin="\n 1.    Rockin Entertainment Service,  ('we' or 'us' or 'our', depending on what makes the most sense) are committed to protecting and respecting your privacy." +
				"\n 2.    This privacy policy sets out the basis on which we will process any personal information we collect from you, or that you provide to us. Please read this privacy policy carefully so that you understand how we will treat your personal information.\n";
		
		String children="\n 3. Rockin Entertainment Service recognize we have a special obligation to protect personal information we obtain from children. We do not and will not knowingly collect information from any unsupervised child under the age of 13. In certain jurisdictions, prior written permission or other verifiable consent may also be required from a parent or guardian for children under the age of 18." ;
		String informationCollect="\n 4. We respect your right to privacy and will only process personal information you provide to us in accordance with applicable privacy laws, such as the Privacy and Electronic Communications (EC Directive) Regulations  within the In." +
				"\n 5. The kinds of data which we may process in order to monitor our Services and improve your user experience include: " +
				"\n - Your name (first and last name). " +
				"\n - Your sex." +
				"\n - Your contact details (such as your email address and/or mobile phone number)." +
				"\n - Your birth date. " +
				"\n - Your screen name and profile picture." +
				"\n - Technical or other details about any device which you use to access the Services: Internet and/or network connection (including your IP address, Media Access Control (MAC); operating system, browser type or other software; hardware (including computer hardware or mobile model); mobile device details (including your mobile device type and number; mobile carrier details; and unique mobile device ID) or other technical details." +
				"\n - Details of your use of our Services including, but not limited to: traffic data, location/geographical data, the Service resources that you access, the time you spend on the Services, crash reports and reasons for connection drops." +
				"\n 6. We may collect and process data about you through the following means: Data that you provide to us via Facebook or which you make publicly available on Facebook, or which you give us via any forms on our Site or by contacting us. Information provided when you report a problem with any of the Services. We may also ask you to complete surveys that we use for research purposes, although you do not have to respond to them." +
				"\n 7. We may collect this data through our Services or third party services connected with them (including in particular Facebook and Google Analytics)" +
				"\n 8. We store the data that we collect from you in our servers in the India. However, this data may be transferred to, and stored at, a location outside of the  . It may also be processed by staff  who work for us or for one of our suppliers. Such staff may be engaged in, among other things, the performance of our obligations from any contracts entered into with you and the provision of support services. By submitting your personal data, you agree to this transfer, storing or processing. We will take all steps reasonably necessary to ensure that your data is treated securely and in accordance with this privacy policy." ;
		String infromationDetail="\n  9. We use information held about you in the following ways:" +
			"\n - To ensure that content from our Services is presented in the most effective manner for you and for your computer or other device from which you access the Services." +
			"\n - To provide you with information or services that you request from us or which we feel may interest you, if you have consented to be contacted for such purposes." +
			"\n - To perform our obligations from any contracts entered into with you." +
			"\n - To allow you to participate in interactive features of our Services, if you choose." +
			"\n - To notify you about changes to our Services." +
			"\n 10. Please remember that any communications you have via our Services, including for example Apps, may reveal your screen name, your profile picture and the content of your communications to other users." +
			"\n 11. We are not responsible for the activities of other users or other third parties to whom you choose to provide your personal information (whether via our Services or otherwise). \n";
		String disclouserdetail="\n 12. We may disclose your personal information to any member of our group, which means our subsidiaries, our ultimate holding company and its subsidiaries." +
				"\n 13. We may disclose your personal information to third parties in the following circumstances:" +
				"\n - In the event that we sell or buy any business or assets, in which case we may disclose your personal information to the seller or buyer of such business or assets." +
				"\n - If we or substantially all of our assets are acquired by a third party, in which case personal information held by us about our customers will be a transferred asset." +
				"\n - If we are under a duty to disclose or share your personal information in order to comply with any legal obligation, or in order to enforce or apply, or to protect our rights, property, or safety of our customers, or others.";
		
		String thirdParty="\n 14. Our Services may, from time to time, contain links to and from the websites of our partner networks, advertisers and affiliates. These websites have their own privacy policies. Please note that if you follow a link to any of these websites, we do not accept any responsibility or liability for these policies, nor do we endorse such websites.";
	    
		String yourrightdetail="\n 15. You have the right to ask us not to process your personal information for marketing purposes. We will usually inform you (before collecting your data) if we intend to use your data for such purposes. You can exercise this right by contacting us at info@rockin.co.in." +
				"\n 16. You can email us at info@rockin.co.in to request that we delete your personal information from our database. We will use commercially reasonable efforts to honor your request. We may retain an archived copy of your records if required to do so by law or for legitimate business purposes.";
		String datasecuritydetail="\n 17. Unfortunately, the transmission of information via the Internet is not completely secure. We will do our best to protect your personal information but we cannot guarantee the security of your data transmitted to our Services, any transmission is at your own risk. We will use strict procedures and security features to try to prevent unauthorized access to your information.";
		
		String changePolicydetail="\n 18. Any changes we may make to our privacy policy in the future will be posted on this page and, where appropriate, notified to you by email.\n" ;
		
		String Contactdetail="\n 19. Please address any questions or comments regarding this privacy policy to info@rockin.co.in";
		policyView = (TextView)findViewById(R.id.policyView);
		introductionView= (TextView)findViewById(R.id.indroctionView);
		rockinView=(TextView)findViewById(R.id.RockinEntertainmentView);
		aboutChildrenView = (TextView)findViewById(R.id.aboutChildrenView);
		aboutChildrenDeclrationView=(TextView)findViewById(R.id.aboutChildrenDeclrationView);
		informationCollectView=(TextView)findViewById(R.id.informationCollectView);
		informationCollectDeclrationView=(TextView)findViewById(R.id.informationCollectDeclrationView);
		informationUses=(TextView)findViewById(R.id.informationUses);
		informationDetailView=(TextView)findViewById(R.id.informationDetailView);
		disclosureofApp=(TextView)findViewById(R.id.disclouserofYourApp);
		disloserDetail=(TextView)findViewById(R.id.disclouserDetail);
		thirdPartyView=(TextView)findViewById(R.id.thirdpartySite);
		thirdPartyDetailView=(TextView)findViewById(R.id.thirdpartySiteView);
		yourRights=(TextView)findViewById(R.id.yourRights);
		yourRightsdetail=(TextView)findViewById(R.id.yourrightDetail);
		datasecurity=(TextView)findViewById(R.id.Datasecurity);
		datasecuritydetailView=(TextView)findViewById(R.id.DatasecurityDetail);
		changePolicy=(TextView)findViewById(R.id.changepolicy);
		changePolicyDetails=(TextView)findViewById(R.id.changepolicyDeatil);
        contact=(TextView)findViewById(R.id.contact);
        contactDetail=(TextView)findViewById(R.id.contactDeatil);
        contactDetail.setText(Contactdetail);
		changePolicyDetails.setText(changePolicydetail);
		datasecuritydetailView.setText(datasecuritydetail);
		yourRightsdetail.setText(yourrightdetail);
		thirdPartyDetailView.setText(thirdParty);
		thirdPartyDetailView.setText(disclouserdetail);
		disloserDetail.setText(disclouserdetail);
		informationDetailView.setText(infromationDetail);
		informationCollectDeclrationView.setText(informationCollect);
		aboutChildrenDeclrationView.setText(children);
		policyView.setText(policy);
		rockinView.setText(rockin);
	}

}
