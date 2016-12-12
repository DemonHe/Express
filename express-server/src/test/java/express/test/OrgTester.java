package express.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import express.data.organiationData.OrganizationIO;
import express.po.OrgProperty;
import express.po.OrganizationPO;

public class OrgTester {

	@Test
	public void test() {
		try {
			OrganizationIO org = new OrganizationIO();
			OrganizationPO o1 = new OrganizationPO("北京","海淀区营业厅","长安街100号",OrgProperty.SALE,"010001");
			OrganizationPO o2 = new OrganizationPO("南京","栖霞区营业厅","仙林大道163号",OrgProperty.SALE,"025001");
			OrganizationPO o3 = new OrganizationPO("上海","浦东区营业厅","静安路100号",OrgProperty.SALE,"021001");
			//org.createOrgInfo(o1);
			//org.createOrgInfo(o2);
			//org.createOrgInfo(o3);
			org.writeAllOrgInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
