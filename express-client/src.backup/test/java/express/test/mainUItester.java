package express.test;

import static org.junit.Assert.*;

import org.junit.Test;

import express.presentation.mainUI.MockMainUI;

public class mainUItester {

	@Test
	public void test() {
		MockMainUI mockm = new MockMainUI(null, null);
		assertEquals(mockm.jumpToadjustUI(),true);
		assertEquals(mockm.jumpToadminAddUI(),true);
		assertEquals(mockm.jumpToadminStartUI(),true);
		assertEquals(mockm.jumpTobusinessArrivalUI(),true);
		assertEquals(mockm.jumpTobusinessDeliverUI(),true);
		assertEquals(mockm.jumpTobusinessDriverUI(),true);
		assertEquals(mockm.jumpTobusinessMenuUI(),true);
		assertEquals(mockm.jumpTobusinessReceiveUI(),true);
		assertEquals(mockm.jumpTobusinessShipmentUI(),true);
		assertEquals(mockm.jumpTobusinessVehicleUI(),true);
		assertEquals(mockm.jumpTodeliverMenuUI(),true);
		assertEquals(mockm.jumpTodeliverOrderUI(),true);
		assertEquals(mockm.jumpTodeliverReceiveUI(),true);
		assertEquals(mockm.jumpToFinanceCreateOperateUI(),true);
		assertEquals(mockm.jumpToFinanceCreateProfitUI(),true);
		assertEquals(mockm.jumpToFinanceInitAccountUI(),true);
		assertEquals(mockm.jumpToFinanceInitBankUI(),true);
		assertEquals(mockm.jumpToFinanceInitOrgUI(),true);
		assertEquals(mockm.jumpToFinanceInitRepoUI(),true);
		assertEquals(mockm.jumpToFinanceInitUserUI(),true);
		assertEquals(mockm.jumpToFinanceMenuUI(),true);
		assertEquals(mockm.jumpToFinancePaymentUI(),true);
		assertEquals(mockm.jumpToFinancePreviousInitUI(),true);
		assertEquals(mockm.jumpToFinanceSumReceiveDocUI(),true);
		assertEquals(mockm.jumpToFinanceSysLogUI(),true);
		assertEquals(mockm.jumpToFinanceVehicleUI(),true);
		assertEquals(mockm.jumpToFinanceViewStatisticUI(),true);
		assertEquals(mockm.jumpToinUI(),true);
		assertEquals(mockm.jumpToinventoryUI(),true);
		assertEquals(mockm.jumpTomanagerExamDocUI(),true);
		assertEquals(mockm.jumpTomanagerMemberUI(),true);
		assertEquals(mockm.jumpTomanagerMenuUI(),true);
		assertEquals(mockm.jumpTomanagerOrgUI(),true);
		assertEquals(mockm.jumpTomanagerStatisticUI(),true);
		assertEquals(mockm.jumpTooutUI(),true);
		assertEquals(mockm.jumpTosearchUI(),true);
		assertEquals(mockm.jumpToshowUI(),true);
		assertEquals(mockm.jumpTotranscenterRepoMeauUI(),true);
		assertEquals(mockm.jumpTotransSaleArrivalDocUI(),true);
		assertEquals(mockm.jumpTotransSaleMenuUI(),true);
		assertEquals(mockm.jumpTotransSaleShipmentDocUI(),true);
		assertEquals(mockm.jumpTotransSaleTransterDocUI(),true);
		assertEquals(mockm.jumpToviewShowUI(),true);
		assertEquals(mockm.jumpToviewUI(),true);
	}

}
