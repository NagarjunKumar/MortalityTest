// 
// Decompiled by Procyon v0.5.36
// 

package ngha.oracle.apps.inv.mortality.webui;

import oracle.apps.fnd.common.VersionInfo;
import ngha.oracle.apps.inv.mortality.server.GetDptnameAndTypeVORowImpl;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import ngha.oracle.apps.inv.mortality.server.DeptHospReviewerEOVORowImpl;
import oracle.jbo.domain.Number;
import ngha.oracle.apps.inv.mortality.server.SangMorApprFrmMrpEOVORowImpl;
import oracle.cabo.ui.UINode;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OACellFormatBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageCheckBoxBean;
import ngha.oracle.apps.inv.mortality.server.xxActionCommiSpellVORowImpl;
import ngha.oracle.apps.inv.mortality.server.xxActionSpellVORowImpl;
import ngha.oracle.apps.inv.mortality.server.ApproverFormSelectManagementEOVORowImpl;
import ngha.oracle.apps.inv.mortality.server.xxCommitteeSpellVORowImpl;
import com.sun.java.util.collections.ArrayList;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import ngha.oracle.apps.inv.mortality.server.xxReviewerCBVORowImpl;
import ngha.oracle.apps.inv.mortality.server.xxTypeSpelVORowImpl;
import java.sql.CallableStatement;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.jbo.Row;
import oracle.apps.fnd.framework.webui.beans.OAImageBean;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import com.sun.java.util.collections.HashMap;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAControllerImpl;

public class ApproverFormSelectManagementCOX1 extends OAControllerImpl
{
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED;
    Boolean mrp;
    Boolean mng;
    Boolean dep;
    Boolean hosp;
    Boolean depmorb;
    Boolean hospmorb;
    Boolean ImpProj;
    Boolean AfterActReview;
    Boolean RootCauseAnalysis;
    Boolean noActionRequired;
    Boolean splTaskForce;
    Boolean depreviwer;
    Boolean hospreviewer;
    String reqid;
    String formtype;
    String DptMortMorb;
    String DptName;
    int rowcount1;
    int rowcount2;
    int rowcount3;
    int rowcount7;
    int rowcount6;
    String mortMorb;
    String dept;
    Boolean CB1;
    Boolean CB2;
    Boolean CB3;
    Boolean CB4;
    Boolean CB5;
    Boolean CB6;
    Boolean CB7;
    Boolean CB8;
    Boolean CB9;
    Boolean CB10;
    Boolean CB11;
    Boolean issaved;
    String transaction;
    String ntfID;
    
    public ApproverFormSelectManagementCOX1() {
        this.$init$();
    }
    
    private void $init$() {
        this.mrp = Boolean.FALSE;
        this.mng = Boolean.FALSE;
        this.dep = Boolean.FALSE;
        this.hosp = Boolean.FALSE;
        this.depmorb = Boolean.FALSE;
        this.hospmorb = Boolean.FALSE;
        this.ImpProj = Boolean.FALSE;
        this.AfterActReview = Boolean.FALSE;
        this.RootCauseAnalysis = Boolean.FALSE;
        this.noActionRequired = Boolean.FALSE;
        this.splTaskForce = Boolean.FALSE;
        this.depreviwer = Boolean.FALSE;
        this.hospreviewer = Boolean.FALSE;
        this.reqid = null;
        this.formtype = null;
        this.DptMortMorb = null;
        this.DptName = null;
        this.rowcount1 = 0;
        this.rowcount2 = 0;
        this.rowcount3 = 0;
        this.rowcount7 = 0;
        this.rowcount6 = 0;
        this.mortMorb = null;
        this.dept = null;
        this.CB1 = Boolean.FALSE;
        this.CB2 = Boolean.FALSE;
        this.CB3 = Boolean.FALSE;
        this.CB4 = Boolean.FALSE;
        this.CB5 = Boolean.FALSE;
        this.CB6 = Boolean.FALSE;
        this.CB7 = Boolean.FALSE;
        this.CB8 = Boolean.FALSE;
        this.CB9 = Boolean.FALSE;
        this.CB10 = Boolean.FALSE;
        this.CB11 = Boolean.FALSE;
        this.issaved = Boolean.FALSE;
        this.transaction = null;
        this.ntfID = null;
    }
    
    public void processRequest(final OAPageContext pageContext, final OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if (pageContext.getParameter("RequestID") != null && !"".equals(pageContext.getParameter("RequestID"))) {
            this.reqid = pageContext.getParameter("RequestID").toString().trim();
            if (pageContext.getParameter("tableType") != null && !"".equals(pageContext.getParameter("tableType"))) {
                this.formtype = pageContext.getParameter("tableType").toString().trim();
                this.hideRegion(am, pageContext);
            }
        }
        this.hideactionTables(am, pageContext);
        this.hideReviewer(am, pageContext);
        if (pageContext.getParameter("reviewer") != null && !"".equals(pageContext.getParameter("reviewer"))) {
            if ("commitee".equalsIgnoreCase(pageContext.getParameter("reviewer")) || "Commitiee".equalsIgnoreCase(pageContext.getParameter("reviewer"))) {
                this.depreviwer = Boolean.TRUE;
                this.hospreviewer = Boolean.TRUE;
                if (pageContext.getParameter("NtfId") != null) {
                    this.ntfID = pageContext.getParameter("NtfId");
                }
                if (pageContext.getParameter("dept") != null && !"".equals(pageContext.getParameter("dept"))) {
                    this.dept = pageContext.getParameter("dept").trim();
                }
                this.setDepartTypeandMortMorbType(am, pageContext, webBean);
                this.hideCommitiee(am, pageContext);
                this.mortMorb = pageContext.getParameter("mortmorb");
                this.hideCommitteeChairRows(am);
            }
            final OAViewObject transVO = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO1");
            if (transVO != null) {
                final OARow transRow = (OARow)transVO.first();
                if (transRow != null) {
                    if (transRow.getAttribute("ReviewerTransient") != null && !"".equals(transRow.getAttribute("ReviewerTransient"))) {
                        transRow.setAttribute("Dummy", (Object)(boolean)Boolean.TRUE);
                    }
                    else {
                        pageContext.putDialogMessage(new OAException("ReviewerTransient NULL"));
                    }
                }
            }
            else {
                pageContext.putDialogMessage(new OAException("VO Null "));
            }
            if (pageContext.getParameter("FromActionPage") != null && !"".equals(pageContext.getParameter("FromActionPage"))) {
                this.loadData(am, pageContext, webBean, this.reqid);
            }
        }
        else {
            final OAMessageStyledTextBean mortmorbBeanTxt = (OAMessageStyledTextBean)webBean.findChildRecursive("DeptMortMorbStyTxt");
            final OAMessageStyledTextBean DepartmentNameTxt = (OAMessageStyledTextBean)webBean.findChildRecursive("DeptMortStylTxt");
            if (mortmorbBeanTxt != null && DepartmentNameTxt != null) {
                mortmorbBeanTxt.setRendered(false);
                DepartmentNameTxt.setRendered(false);
            }
            this.loadData(am, pageContext, webBean, this.reqid);
        }
        if (pageContext.getParameter("searchpage") != null && !"".equals(pageContext.getParameter("searchpage"))) {
            if ("true".equalsIgnoreCase(pageContext.getParameter("searchpage"))) {
                if (pageContext.getParameter("dept") != null && !"".equals(pageContext.getParameter("dept"))) {
                    this.dept = pageContext.getParameter("dept").trim();
                }
                this.loadData(am, pageContext, webBean, this.reqid);
            }
            else if (pageContext.getParameter("transaction") != null && !"".equals(pageContext.getParameter("transaction"))) {
                this.transaction = pageContext.getParameter("transaction");
            }
        }
        else if (!pageContext.isBackNavigationFired(false)) {
            TransactionUnitHelper.startTransactionUnit(pageContext, "apprtransaction");
            if (!pageContext.isFormSubmission()) {
                this.checkMainTableRowCount(am, pageContext, webBean);
                this.disableCB(am);
            }
        }
    }
    
    public void processFormRequest(final OAPageContext pageContext, final OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        if (pageContext.getParameter("MRP") != null) {
            final HashMap hashMap1 = new HashMap();
            hashMap1.put((Object)"tableType", (Object)this.formtype);
            hashMap1.put((Object)"RequestID", (Object)this.reqid);
            hashMap1.put((Object)"dept", (Object)"MRP");
            hashMap1.put((Object)"ActionPage", (Object)"Yes");
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormPatientComplaintPG", (String)null, (byte)0, (String)null, hashMap1, true, "Y", (byte)99);
        }
        if (pageContext.getParameter("MNG") != null) {
            final HashMap hashMap2 = new HashMap();
            hashMap2.put((Object)"tableType", (Object)this.formtype);
            hashMap2.put((Object)"RequestID", (Object)this.reqid);
            hashMap2.put((Object)"dept", (Object)"MNG");
            hashMap2.put((Object)"ActionPage", (Object)"Yes");
            final OAMessageChoiceBean CaseReview = (OAMessageChoiceBean)webBean.findChildRecursive("YesrNoMC");
            if (CaseReview == null || CaseReview.getValue(pageContext) == null) {
                throw new OAException("Please Select Case Review Required Yes or No");
            }
            if ("Yes".equalsIgnoreCase(String.valueOf(CaseReview.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormChairmanReviewYesPG", (String)null, (byte)0, (String)null, hashMap2, true, "Y", (byte)99);
            }
            else if ("No".equalsIgnoreCase(String.valueOf(CaseReview.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormChairmanReviewNoPG", (String)null, (byte)0, (String)null, hashMap2, true, "Y", (byte)99);
            }
        }
        if (pageContext.getParameter("DPT") != null) {
            am.invokeMethod("apply");
            final HashMap hashMap3 = new HashMap();
            hashMap3.put((Object)"tableType", (Object)this.formtype);
            hashMap3.put((Object)"RequestID", (Object)this.reqid);
            hashMap3.put((Object)"dept", (Object)"DPT");
            hashMap3.put((Object)"ActionPage", (Object)"Yes");
            if (this.depreviwer) {
                hashMap3.put((Object)"reviewer", (Object)"Commitiee");
            }
            else {
                hashMap3.put((Object)"reviewer", (Object)"QM");
            }
            final OAMessageChoiceBean DepartmentName = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMC");
            if (DepartmentName == null || DepartmentName.getValue(pageContext) == null) {
                throw new OAException("Please Select Department Name");
            }
            hashMap3.put((Object)"DepartmentName", DepartmentName.getValue(pageContext));
            final OAMessageChoiceBean mortmorbBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMorbMC");
            if (mortmorbBean == null || mortmorbBean.getValue(pageContext) == null) {
                throw new OAException("Please Select Mortality Morbidity Type for Department Action");
            }
            if ("Mortality".equalsIgnoreCase(String.valueOf(mortmorbBean.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/DepartmentMortalityPG", (String)null, (byte)0, (String)null, hashMap3, true, "Y", (byte)99);
            }
            else if ("Morbidity".equalsIgnoreCase(String.valueOf(mortmorbBean.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/DepartmentMorbidityPG", (String)null, (byte)0, (String)null, hashMap3, true, "Y", (byte)99);
            }
        }
        if (pageContext.getParameter("HOSMort") != null) {
            am.invokeMethod("apply");
            final HashMap hashMap4 = new HashMap();
            hashMap4.put((Object)"tableType", (Object)this.formtype);
            hashMap4.put((Object)"RequestID", (Object)this.reqid);
            hashMap4.put((Object)"dept", (Object)"HOS");
            hashMap4.put((Object)"ActionPage", (Object)"Yes");
            hashMap4.put((Object)"MortMorbType", (Object)"Mortality");
            if (this.hospreviewer) {
                hashMap4.put((Object)"reviewer", (Object)"Commitiee");
            }
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/morbidity/webui/HospitalMorbidityReviewPG", (String)null, (byte)0, (String)null, hashMap4, true, "Y", (byte)99);
        }
        if (pageContext.getParameter("HOSMorb") != null) {
            am.invokeMethod("apply");
            final HashMap hashMap5 = new HashMap();
            hashMap5.put((Object)"tableType", (Object)this.formtype);
            hashMap5.put((Object)"RequestID", (Object)this.reqid);
            hashMap5.put((Object)"dept", (Object)"HOS");
            hashMap5.put((Object)"ActionPage", (Object)"Yes");
            hashMap5.put((Object)"MortMorbType", (Object)"Morbidity");
            if (this.hospreviewer) {
                hashMap5.put((Object)"reviewer", (Object)"commitee");
            }
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/morbidity/webui/HospitalMorbidityReviewPG", (String)null, (byte)0, (String)null, hashMap5, true, "Y", (byte)99);
        }
        if (pageContext.getParameter("ROC") != null) {
            final OAViewObject deptvo = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO6");
            if (deptvo != null) {
                deptvo.setWhereClause((String)null);
                deptvo.setWhereClauseParams((Object[])null);
                deptvo.executeQuery();
                if (deptvo.getRowCount() <= 0) {
                    throw new OAException("Add committiee chair members to proceed");
                }
            }
            am.invokeMethod("apply");
            final HashMap hashMap6 = new HashMap();
            hashMap6.put((Object)"tableType", (Object)this.formtype);
            hashMap6.put((Object)"RequestID", (Object)this.reqid);
            hashMap6.put((Object)"dept", (Object)"ROC");
            hashMap6.put((Object)"ActionPage", (Object)"Yes");
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/RootCauseAnalysisPG", (String)null, (byte)0, (String)null, hashMap6, true, "Y", (byte)99);
        }
        if (pageContext.getParameter("AFA") != null) {
            final OAViewObject deptvo2 = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO7");
            if (deptvo2 != null) {
                deptvo2.setWhereClause((String)null);
                deptvo2.setWhereClauseParams((Object[])null);
                deptvo2.executeQuery();
                if (deptvo2.getRowCount() <= 0) {
                    throw new OAException("Add committiee chair members to proceed");
                }
            }
            am.invokeMethod("apply");
            final HashMap hashMap7 = new HashMap();
            hashMap7.put((Object)"tableType", (Object)this.formtype);
            hashMap7.put((Object)"RequestID", (Object)this.reqid);
            hashMap7.put((Object)"dept", (Object)"AFA");
            hashMap7.put((Object)"ParentRole", (Object)"Edit");
            hashMap7.put((Object)"ActionPage", (Object)"Yes");
            hashMap7.put((Object)"reviewer", (Object)"QM");
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/afterActionReview/webui/AfterActionReviewPG", (String)null, (byte)0, (String)null, hashMap7, true, "Y", (byte)99);
        }
        if (pageContext.getParameter("STF") != null) {
            am.invokeMethod("apply");
            final HashMap hashMap8 = new HashMap();
            hashMap8.put((Object)"tableType", (Object)this.formtype);
            hashMap8.put((Object)"RequestID", (Object)this.reqid);
            hashMap8.put((Object)"dept", (Object)"STF");
            hashMap8.put((Object)"ActionPage", (Object)"Yes");
            final OAMessageChoiceBean mortmorbBean2 = (OAMessageChoiceBean)webBean.findChildRecursive("mortalityMC1");
            if (mortmorbBean2 == null || mortmorbBean2.getValue(pageContext) == null) {
                throw new OAException("Please Select Mortality Morbidity Type for Special Task Force Action");
            }
            final OAViewObject deptvo3 = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO8");
            if (deptvo3 != null) {
                deptvo3.setWhereClause((String)null);
                deptvo3.setWhereClauseParams((Object[])null);
                deptvo3.executeQuery();
                if (deptvo3.getRowCount() <= 0) {
                    throw new OAException("Add committiee chair members to proceed");
                }
            }
            if ("Mortality".equalsIgnoreCase(String.valueOf(mortmorbBean2.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/SpecialTaskForceMortalityPG", (String)null, (byte)0, (String)null, hashMap8, true, "Y", (byte)99);
            }
            else if ("Morbidity".equalsIgnoreCase(String.valueOf(mortmorbBean2.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/SpecialTaskForceMorbidityPG", (String)null, (byte)0, (String)null, hashMap8, true, "Y", (byte)99);
            }
            else if ("Other".equalsIgnoreCase(String.valueOf(mortmorbBean2.getValue(pageContext)))) {
                pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/SpecialTaskForceOtherPG", (String)null, (byte)0, (String)null, hashMap8, true, "Y", (byte)99);
            }
        }
        if (pageContext.getParameter("IMP") != null) {
            am.invokeMethod("apply");
            final HashMap hashMap9 = new HashMap();
            hashMap9.put((Object)"tableType", (Object)this.formtype);
            hashMap9.put((Object)"RequestID", (Object)this.reqid);
            hashMap9.put((Object)"dept", (Object)"IMP");
            hashMap9.put((Object)"ActionPage", (Object)"Yes");
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ImprovementProject", (String)null, (byte)0, (String)null, hashMap9, true, "Y", (byte)99);
        }
        System.out.println("event param in appr form is : " + pageContext.getParameter("event"));
        if ("AddMrpRow".equals(pageContext.getParameter("event"))) {
            this.createNewMRPRow(am, pageContext);
        }
        if ("AddMngRow".equals(pageContext.getParameter("event"))) {
            final OAMessageChoiceBean CaseReview2 = (OAMessageChoiceBean)webBean.findChildRecursive("YesrNoMC");
            if (CaseReview2 == null || CaseReview2.getValue(pageContext) == null) {
                throw new OAException("Please Select Case Review Required Yes or No");
            }
            this.createNewMNGRow(am, pageContext, webBean);
        }
        if ("AddDptRow".equals(pageContext.getParameter("event"))) {
            final OAMessageChoiceBean Deptname = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMC");
            final OAMessageChoiceBean MortMorb = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMorbMC");
            if (Deptname == null || Deptname.getValue(pageContext) == null || MortMorb == null || MortMorb.getValue(pageContext) == null) {
                throw new OAException("Please Select Department and Mort Morb");
            }
            this.createNewDPTRow(am, pageContext, webBean);
        }
        if ("AddDptRowRev".equals(pageContext.getParameter("event"))) {
            this.createNewDPTReviewersRow(am, pageContext, webBean);
        }
        if ("AddHosRow".equals(pageContext.getParameter("event"))) {
            this.createNewHosMortReviewersRow(am, pageContext, webBean);
        }
        if ("AddHosCommRow".equals(pageContext.getParameter("event"))) {
            this.createNewHOSRow(am, pageContext, webBean);
        }
        if ("AddHosMorbRow".equals(pageContext.getParameter("event"))) {
            this.createNewHOSMorbReviewersRow(am, pageContext, webBean);
        }
        if ("AddHosMorbCommRow".equals(pageContext.getParameter("event"))) {
            this.createNewHOSMorbRow(am, pageContext, webBean);
        }
        if ("AddRocRow".equals(pageContext.getParameter("event"))) {
            this.createNewROCRow(am, pageContext, webBean);
        }
        if ("AddAfaRow".equals(pageContext.getParameter("event"))) {
            this.createNewAFARow(am, pageContext, webBean);
        }
        if ("AddStfRow".equals(pageContext.getParameter("event"))) {
            final OAMessageChoiceBean MortMorb2 = (OAMessageChoiceBean)webBean.findChildRecursive("mortalityMC1");
            if (MortMorb2 == null || MortMorb2.getValue(pageContext) == null) {
                throw new OAException("Please Select  Mort Morb type");
            }
            this.createNewSTFRow(am, pageContext, webBean);
        }
        if ("apprLink".equals(pageContext.getParameter("event")) && !TransactionUnitHelper.isTransactionUnitInProgress(pageContext, "apprtransaction", true)) {
            System.out.println("appr link called ");
            this.loadData(am, pageContext, webBean, this.reqid);
        }
        if ("cb1".equals(pageContext.getParameter("event")) || "cb2".equals(pageContext.getParameter("event")) || "cb3".equals(pageContext.getParameter("event")) || "cb4".equals(pageContext.getParameter("event")) || "cb5".equals(pageContext.getParameter("event")) || "cb6".equals(pageContext.getParameter("event")) || "cb7".equals(pageContext.getParameter("event")) || "cb8".equals(pageContext.getParameter("event")) || "cb10".equals(pageContext.getParameter("event")) || "cb11".equals(pageContext.getParameter("event"))) {
            if ("on".equals(pageContext.getParameter("checkbox1")) || "on".equals(pageContext.getParameter("checkbox2")) || "on".equals(pageContext.getParameter("checkbox3")) || "on".equals(pageContext.getParameter("checkbox4")) || "on".equals(pageContext.getParameter("checkbox5")) || "on".equals(pageContext.getParameter("checkbox6")) || "on".equals(pageContext.getParameter("checkbox7")) || "on".equals(pageContext.getParameter("checkbox8")) || "on".equals(pageContext.getParameter("checkbox10")) || "on".equals(pageContext.getParameter("checkbox11"))) {
                this.disableCBGrp2(am);
            }
            else {
                this.enableCB(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox1"))) {
                this.CB1 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB1 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox2"))) {
                this.CB2 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB2 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox3"))) {
                this.CB3 = Boolean.TRUE;
                this.mortMorb = "Mortality";
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB3 = Boolean.FALSE;
                this.mortMorb = null;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox10"))) {
                this.CB10 = Boolean.TRUE;
                this.mortMorb = "Morbidity";
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB10 = Boolean.FALSE;
                this.mortMorb = null;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox4"))) {
                this.CB4 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
                this.mortMorb = "Mortality";
            }
            else {
                this.CB4 = Boolean.FALSE;
                this.mortMorb = null;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox11"))) {
                this.CB11 = Boolean.TRUE;
                this.mortMorb = "Morbidity";
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB11 = Boolean.FALSE;
                this.mortMorb = null;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox5"))) {
                this.CB5 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB5 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox6"))) {
                this.CB6 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB6 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox7"))) {
                this.CB7 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB7 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
            if ("on".equals(pageContext.getParameter("checkbox8"))) {
                this.CB8 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.CB8 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
        }
        if ("cb9".equals(pageContext.getParameter("event"))) {
            if ("on".equals(pageContext.getParameter("checkbox9"))) {
                this.disableCBGrp1(am);
                this.CB9 = Boolean.TRUE;
                this.showactionTables(am, pageContext);
            }
            else {
                this.enableCB(am);
                this.CB9 = Boolean.FALSE;
                this.showPartiallyactionTables(am);
            }
        }
        if (pageContext.getParameter("Save") != null) {
            this.validatefields(pageContext, am);
            if ("on".equals(pageContext.getParameter("checkbox8"))) {
                this.validateImprovementProject(pageContext, webBean);
            }
            this.saveData(pageContext, webBean);
            am.invokeMethod("apply");
            this.callProcedures(am, pageContext);
        }
        if (pageContext.getParameter("Back") != null) {
            final OAException descMesg = new OAException("Do you wish to return Dashboard ?");
            final OADialogPage dialogPage = new OADialogPage((byte)1, descMesg, (OAException)null, "", "");
            dialogPage.setOkButtonToPost(true);
            dialogPage.setNoButtonToPost(true);
            dialogPage.setPostToCallingPage(true);
            dialogPage.setOkButtonItemName("Yes");
            dialogPage.setNoButtonItemName("No");
            dialogPage.setOkButtonLabel("YES");
            dialogPage.setNoButtonLabel("NO");
            pageContext.redirectToDialogPage(dialogPage);
        }
        else if (pageContext.getParameter("Yes") != null) {
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/XXMortalityandMorbidityDashBoardPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "Y", (byte)99);
        }
        else if (pageContext.getParameter("No") != null && !TransactionUnitHelper.isTransactionUnitInProgress(pageContext, "apprtransaction", true)) {
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormSelectManagementPG&RequestID=" + this.reqid, (String)null, (byte)0, (String)null, (HashMap)null, true, "Y", (byte)99);
        }
        if (pageContext.getParameter("okay") != null) {
            this.CloseFYINotification(pageContext, am);
            pageContext.setForwardURL("OA.jsp?OAFunc=OAHOMEPAGE", (String)null, (byte)0, (String)null, (HashMap)null, true, "Y", (byte)99);
        }
        else if (pageContext.getParameter("okayC") != null) {
            this.CloseFYINotification(pageContext, am);
            pageContext.setForwardURL("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/XXMortalityandMorbidityDashBoardPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "Y", (byte)99);
        }
        if ("insertRow1".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa1 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO1");
            if (!oa1.isPreparedForExecution()) {
                oa1.setWhereClause("1=2");
                oa1.executeQuery();
            }
            final Row row1 = oa1.createRow();
            oa1.insertRow(row1);
            row1.setNewRowState((byte)(-1));
            this.rowcount1 = oa1.getRowCount();
        }
        else if ("insertRow2".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa2 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO2");
            if (!oa2.isPreparedForExecution()) {
                oa2.setWhereClause("1=2");
                oa2.executeQuery();
            }
            final Row row2 = oa2.createRow();
            oa2.insertRow(row2);
            row2.setNewRowState((byte)(-1));
            this.rowcount2 = oa2.getRowCount();
        }
        else if ("insertRow3".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa3 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO3");
            if (!oa3.isPreparedForExecution()) {
                oa3.setWhereClause("1=2");
                oa3.executeQuery();
            }
            final Row row3 = oa3.createRow();
            oa3.insertRow(row3);
            row3.setNewRowState((byte)(-1));
            this.rowcount3 = oa3.getRowCount();
            if (pageContext.getParameter("reviewer") == null && this.rowcount3 >= 1) {
                final OAImageBean adbean = (OAImageBean)webBean.findChildRecursive("AddRow3");
                if (adbean != null) {
                    adbean.setRendered(false);
                }
            }
        }
        else if ("insertRow8".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa4 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO8");
            if (!oa4.isPreparedForExecution()) {
                oa4.setWhereClause("1=2");
                oa4.executeQuery();
            }
            final Row row4 = oa4.createRow();
            oa4.insertRow(row4);
            row4.setNewRowState((byte)(-1));
            final int rowcount8 = oa4.getRowCount();
            System.out.println("line 497 in Approver CO is " + oa4.getRowCount());
            if (pageContext.getParameter("reviewer") == null && rowcount8 >= 1) {
                final OAImageBean adbean2 = (OAImageBean)webBean.findChildRecursive("AddRow10");
                if (adbean2 != null) {
                    adbean2.setRendered(false);
                }
            }
        }
        else if ("insertRow9".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa5 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO9");
            if (!oa5.isPreparedForExecution()) {
                oa5.setWhereClause("1=2");
                oa5.executeQuery();
            }
            final Row row5 = oa5.createRow();
            oa5.insertRow(row5);
            row5.setNewRowState((byte)(-1));
            final int rowcount9 = oa5.getRowCount();
            System.out.println("line 520 in Approver CO is " + oa5.getRowCount());
            if (pageContext.getParameter("reviewer") == null && rowcount9 >= 1) {
                final OAImageBean adbean3 = (OAImageBean)webBean.findChildRecursive("AddRow11");
                if (adbean3 != null) {
                    adbean3.setRendered(false);
                }
            }
        }
        else if ("insertRow4".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa6 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO4");
            if (!oa6.isPreparedForExecution()) {
                oa6.setWhereClause("1=2");
                oa6.executeQuery();
            }
            final Row row6 = oa6.createRow();
            oa6.insertRow(row6);
            row6.setNewRowState((byte)(-1));
            final int rowcount10 = oa6.getRowCount();
            if (pageContext.getParameter("reviewer") == null && rowcount10 >= 1) {
                final OAImageBean adbean4 = (OAImageBean)webBean.findChildRecursive("AddRow4");
                if (adbean4 != null) {
                    adbean4.setRendered(false);
                }
            }
        }
        else if ("insertRow5".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa7 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO5");
            if (!oa7.isPreparedForExecution()) {
                oa7.setWhereClause("1=2");
                oa7.executeQuery();
            }
            final Row row7 = oa7.createRow();
            oa7.insertRow(row7);
            row7.setNewRowState((byte)(-1));
        }
        else if ("insertRow6".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa8 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO6");
            if (!oa8.isPreparedForExecution()) {
                oa8.setWhereClause("1=2");
                oa8.executeQuery();
            }
            final Row row8 = oa8.createRow();
            oa8.insertRow(row8);
            row8.setNewRowState((byte)(-1));
            this.rowcount6 = oa8.getRowCount();
            System.out.println("approver form row 6 row count is : " + oa8.getRowCount());
            if (pageContext.getParameter("reviewer") == null && this.rowcount6 >= 1) {
                final OAImageBean adbean5 = (OAImageBean)webBean.findChildRecursive("AddRow6");
                if (adbean5 != null) {
                    adbean5.setRendered(false);
                }
            }
        }
        else if ("insertRow7".equals(pageContext.getParameter("event"))) {
            final OAViewObject oa9 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO7");
            if (!oa9.isPreparedForExecution()) {
                oa9.setWhereClause("1=2");
                oa9.executeQuery();
            }
            final Row row9 = oa9.createRow();
            oa9.insertRow(row9);
            row9.setNewRowState((byte)(-1));
            this.rowcount7 = oa9.getRowCount();
        }
        if ("delete1".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
        }
        else if ("delete2".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
        }
        else if ("delete3".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean6 = (OAImageBean)webBean.findChildRecursive("AddRowDPTNew");
            if (adbean6 != null) {
                adbean6.setRendered(true);
            }
        }
        else if ("delete10".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean7 = (OAImageBean)webBean.findChildRecursive("AddRowHOSNewCom");
            if (adbean7 != null) {
                adbean7.setRendered(true);
            }
        }
        else if ("delete11".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean8 = (OAImageBean)webBean.findChildRecursive("AddRowHOSMorbCom");
            if (adbean8 != null) {
                adbean8.setRendered(true);
            }
        }
        else if ("delete4".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean9 = (OAImageBean)webBean.findChildRecursive("AddRowHOSNew");
            if (adbean9 != null) {
                adbean9.setRendered(true);
            }
        }
        else if ("delete5".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean10 = (OAImageBean)webBean.findChildRecursive("AddRowStfNew");
            if (adbean10 != null) {
                adbean10.setRendered(true);
            }
        }
        else if ("delete6".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean11 = (OAImageBean)webBean.findChildRecursive("AddRowROCNew");
            if (adbean11 != null) {
                adbean11.setRendered(true);
            }
        }
        else if ("delete7".equals(pageContext.getParameter("event"))) {
            this.deleteRow(pageContext, webBean);
            final OAImageBean adbean12 = (OAImageBean)webBean.findChildRecursive("AddRowAFANew");
            if (adbean12 != null) {
                adbean12.setRendered(true);
            }
        }
    }
    
    public void deleteRow(final OAPageContext pageContext, final OAWebBean webBean) {
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        final String rowRef = pageContext.getParameter("evtSrcRowRef");
        final OARow row = (OARow)am.findRowByRef(rowRef);
        if (row != null) {
            if (Boolean.valueOf("" + row.getAttribute("PreviouslyAdded"))) {
                pageContext.putDialogMessage(new OAException("Can not delete the row as it was submitted previously."));
            }
            else {
                row.remove();
            }
        }
    }
    
    public void saveData(final OAPageContext pageContext, final OAWebBean webBean) {
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        final String improvementProjectcb = String.valueOf(pageContext.getParameter("checkbox8"));
        final String noActionrequiredcb = String.valueOf(pageContext.getParameter("checkbox9"));
        if ("on".equals(improvementProjectcb)) {
            final OAViewObject XXVO1 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
            if (XXVO1 != null) {
                if (!XXVO1.isPreparedForExecution()) {
                    XXVO1.executeQuery();
                }
                final OARow XXRow = (OARow)XXVO1.createRow();
                XXRow.setNewRowState((byte)(-1));
                XXRow.setAttribute("RequestId", (Object)pageContext.getParameter("RequestID"));
                XXRow.setAttribute("Attribute1", (Object)"In-Progress");
                if (this.mortMorb != null) {
                    XXRow.setAttribute("Attribute2", (Object)this.mortMorb);
                }
                XXRow.setAttribute("ProjectDescription", (Object)pageContext.getParameter("ProjDescTxt"));
                XXRow.setAttribute("ManagementName", (Object)"IMP");
                XXRow.setAttribute("ManagementId", (Object)(pageContext.getParameter("RequestID") + "IMP"));
                XXRow.setAttribute("ProjectName", (Object)pageContext.getParameter("ProjNameTxt"));
                if (!"".equals(pageContext.getParameter("formvalPL")) && pageContext.getParameter("formvalPL") != null) {
                    XXRow.setAttribute("EmployeeId", (Object)pageContext.getParameter("formvalPL"));
                }
                XXRow.setAttribute("ProjectLead", (Object)pageContext.getParameter("ProjectLeadLOV"));
                XXRow.setAttribute("CreateBy", (Object)pageContext.getUserName());
                if (!"".equals(pageContext.getParameter("ProjectLeadCB"))) {
                    XXRow.setAttribute("ProjectLeadCb", (Object)pageContext.getParameter("ProjectLeadCB"));
                }
                System.out.println("line 690 trans id is : " + XXRow.getAttribute("TransactionId"));
            }
            this.ImpProj = Boolean.TRUE;
        }
        if ("on".equals(noActionrequiredcb)) {
            final OAViewObject XXVO2 = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
            if (XXVO2 != null) {
                if (!XXVO2.isPreparedForExecution()) {
                    XXVO2.executeQuery();
                }
                final OARow XXRow2 = (OARow)XXVO2.createRow();
                XXRow2.setNewRowState((byte)(-1));
                XXRow2.setAttribute("RequestId", (Object)pageContext.getParameter("RequestID"));
                XXRow2.setAttribute("ManagementName", (Object)"NAR");
                XXRow2.setAttribute("ManagementId", (Object)(pageContext.getParameter("RequestID") + "NAR"));
                XXRow2.setAttribute("Attribute1", (Object)"Closed");
                XXRow2.setAttribute("NoActionRequired", (Object)pageContext.getParameter("commentsTxt"));
                XXRow2.setAttribute("CreateBy", (Object)pageContext.getUserName());
                System.out.println("line 717 trans id is : " + XXRow2.getAttribute("TransactionId"));
            }
            am.getTransaction().commit();
            this.noActionRequired = Boolean.TRUE;
        }
    }
    
    public void createRowForReviwer(final OAPageContext pageContext, final OAWebBean webBean, final OARow currrow, final String managementcode, final String cbval) {
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        final OAViewObject XXApproverVO = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
        if (XXApproverVO != null) {
            if (!XXApproverVO.isPreparedForExecution()) {
                XXApproverVO.executeQuery();
            }
            final OARow XXRow = (OARow)XXApproverVO.createRow();
            XXRow.setNewRowState((byte)(-1));
            try {
                XXRow.setAttribute("RequestId", (Object)pageContext.getParameter("RequestID"));
            }
            catch (Exception e) {
                throw new OAException("error in yes no " + e.getMessage());
            }
            XXRow.setAttribute("ReviewerCb", currrow.getAttribute("ReviewerCB"));
            XXRow.setAttribute("Attribute6", currrow.getAttribute("yesrNo"));
            if (currrow.getAttribute("ReviewerTransient") != null && !"".equals(currrow.getAttribute("ReviewerTransient"))) {
                XXRow.setAttribute("ReviewerName", currrow.getAttribute("ReviewerTransient"));
            }
            XXRow.setAttribute("ManagementId", (Object)(pageContext.getParameter("RequestID") + managementcode));
            XXRow.setAttribute("ManagementName", (Object)managementcode);
            XXRow.setAttribute("CreateBy", (Object)pageContext.getUserName());
            XXRow.setAttribute("EmployeeId", currrow.getAttribute("empid"));
            XXRow.setAttribute("Attribute1", (Object)"In-Progress");
            XXRow.setAttribute("Attribute7", (Object)"Reviewer");
            if (managementcode.equalsIgnoreCase("MRP")) {
                XXRow.setAttribute("MrpReviewCb", (Object)cbval);
            }
            if ("on".equals(pageContext.getParameter("checkbox7"))) {
                final OAMessageChoiceBean mortmorbBean = (OAMessageChoiceBean)webBean.findChildRecursive("mortalityMC1");
                if (mortmorbBean != null) {
                    XXRow.setAttribute("Attribute8", mortmorbBean.getValue(pageContext));
                }
            }
            if ("on".equals(pageContext.getParameter("checkbox3")) || "on".equals(pageContext.getParameter("checkbox4"))) {
                XXRow.setAttribute("Attribute8", (Object)"Mortality");
            }
            if ("on".equals(pageContext.getParameter("checkbox10")) || "on".equals(pageContext.getParameter("checkbox11"))) {
                XXRow.setAttribute("Attribute8", (Object)"Morbidity");
            }
            if (managementcode.equalsIgnoreCase("MNG")) {
                XXRow.setAttribute("ManagerCb", (Object)cbval);
            }
            if (managementcode.equalsIgnoreCase("DPT")) {
                XXRow.setAttribute("DeptMortalityCb", (Object)cbval);
            }
            if (managementcode.equalsIgnoreCase("HOS")) {
                XXRow.setAttribute("HospMortalityCb", (Object)cbval);
            }
            if (managementcode.equalsIgnoreCase("STF")) {
                XXRow.setAttribute("SpecialTaskCb", (Object)cbval);
            }
            XXApproverVO.insertRow((Row)XXRow);
        }
    }
    
    public void createRowForCommitteeChair(final OAPageContext pageContext, final OAWebBean webBean, final OARow currrow, final String managementcode, final String cbval, final String mortmorbtype) {
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        final OAViewObject XXApproverVO = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
        if (XXApproverVO != null) {
            if (!XXApproverVO.isPreparedForExecution()) {
                XXApproverVO.executeQuery();
            }
            final OARow XXRow = (OARow)XXApproverVO.createRow();
            XXRow.setNewRowState((byte)(-1));
            try {
                XXRow.setAttribute("RequestId", (Object)pageContext.getParameter("RequestID"));
            }
            catch (Exception e) {
                throw new OAException("error in yes no " + e.getMessage());
            }
            final OAMessageChoiceBean deptMorbMCBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMorbMC");
            if (deptMorbMCBean != null && deptMorbMCBean.getValue(pageContext) != null) {
                XXRow.setAttribute("DepartmentMorb", deptMorbMCBean.getValue(pageContext));
            }
            final OAMessageChoiceBean deptMortMCBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMC");
            if (deptMortMCBean != null && deptMortMCBean.getValue(pageContext) != null) {
                XXRow.setAttribute("DepartmentMort", deptMortMCBean.getValue(pageContext));
            }
            XXRow.setAttribute("ReviewerCb", currrow.getAttribute("ReviewerCB"));
            XXRow.setAttribute("Attribute6", currrow.getAttribute("yesrNo"));
            XXRow.setAttribute("ManagementId", (Object)(pageContext.getParameter("RequestID") + managementcode));
            XXRow.setAttribute("ManagementName", (Object)managementcode);
            XXRow.setAttribute("CreateBy", (Object)pageContext.getUserName());
            XXRow.setAttribute("EmployeeId", currrow.getAttribute("empid"));
            XXRow.setAttribute("Attribute1", (Object)"In-Progress");
            XXRow.setAttribute("Attribute3", (Object)"Committee chair person");
            XXRow.setAttribute("Attribute2", currrow.getAttribute("ReviewerTransient"));
            if (mortmorbtype != null) {
                XXRow.setAttribute("Attribute8", (Object)mortmorbtype);
            }
            if (managementcode.equalsIgnoreCase("DPT")) {
                XXRow.setAttribute("DeptMortalityCb", (Object)cbval);
            }
            if (managementcode.equalsIgnoreCase("HOS")) {
                XXRow.setAttribute("HospMortalityCb", (Object)cbval);
            }
            XXApproverVO.insertRow((Row)XXRow);
        }
    }
    
    public String callTheProcemrp(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin  SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_PROCESS_FYIEMAIL(:1); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid);
            oraclecallablestatement.execute();
            return null;
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating MRP  Procedure:" + e);
        }
    }
    
    public String callTheProcemng(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin  SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_PROCESS_FYI_CHARIMAN(:1); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid);
            oraclecallablestatement.execute();
            return null;
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating MNG  Procedure:" + e);
        }
    }
    
    public String callTheProceDepartment(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_COMMITEE_CHAIR_REQ_SUP(:1,:2); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid);
            oraclecallablestatement.setString(2, "DPT");
            oraclecallablestatement.execute();
            return null;
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating Dept  Procedure:" + e);
        }
    }
    
    public String callTheProceHospital(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_COMMITEE_CHAIR_REQ_SUP(:1,:2); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid);
            oraclecallablestatement.setString(2, "HOS");
            oraclecallablestatement.execute();
            return null;
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating Hosp  Procedure:" + e);
        }
    }
    
    private void hideRegion(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject spellVo1 = (OAViewObject)am.findViewObject("xxTypeSpelVO1");
        if (spellVo1 != null) {
            spellVo1.executeQuery();
            final xxTypeSpelVORowImpl row1 = (xxTypeSpelVORowImpl)spellVo1.createRow();
            spellVo1.insertRow((Row)row1);
            row1.setNewRowState((byte)(-1));
            if (row1 != null) {
                if ("PATCOM".equalsIgnoreCase(this.formtype.trim())) {
                    row1.setManualCases(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setPatientComplaint(Boolean.valueOf((boolean)Boolean.TRUE));
                    row1.setHarmScreening(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setMortScreening(Boolean.valueOf((boolean)Boolean.FALSE));
                    this.executePatComplaint(am, pageContext);
                }
                if ("MORMCS".equalsIgnoreCase(this.formtype.trim())) {
                    row1.setManualCases(Boolean.valueOf((boolean)Boolean.TRUE));
                    row1.setPatientComplaint(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setHarmScreening(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setMortScreening(Boolean.valueOf((boolean)Boolean.FALSE));
                    this.executeManualCases(am);
                }
                if ("MORSCR".equalsIgnoreCase(this.formtype.trim())) {
                    row1.setManualCases(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setPatientComplaint(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setHarmScreening(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setMortScreening(Boolean.valueOf((boolean)Boolean.TRUE));
                    this.executeMorScreen(am);
                }
                if ("HARSCR".equalsIgnoreCase(this.formtype.trim())) {
                    row1.setManualCases(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setPatientComplaint(Boolean.valueOf((boolean)Boolean.FALSE));
                    row1.setHarmScreening(Boolean.valueOf((boolean)Boolean.TRUE));
                    row1.setMortScreening(Boolean.valueOf((boolean)Boolean.FALSE));
                    this.executeHarmScreen(am);
                }
            }
        }
    }
    
    public void executePatComplaint(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject patEOVO = (OAViewObject)am.findViewObject("PatientComplaintEOVO2");
        if (patEOVO != null) {
            patEOVO.setWhereClause((String)null);
            patEOVO.setWhereClauseParams((Object[])null);
            patEOVO.setWhereClause(" REQUEST_ID= '" + this.reqid.trim() + "'");
            patEOVO.executeQuery();
        }
    }
    
    private void executeManualCases(final OAApplicationModule am) {
        final OAViewObject patEOVO = (OAViewObject)am.findViewObject("ManualCasesEOVO2");
        if (patEOVO != null) {
            patEOVO.setWhereClause((String)null);
            patEOVO.setWhereClause("REQUEST_ID= '" + this.reqid.trim() + "'");
            patEOVO.executeQuery();
        }
    }
    
    private void executeMorScreen(final OAApplicationModule am) {
        final OAViewObject patEOVO = (OAViewObject)am.findViewObject("MorScreeningEOVO2");
        if (patEOVO != null) {
            patEOVO.setWhereClause((String)null);
            patEOVO.setWhereClause("REQUEST_ID= '" + this.reqid.trim() + "'");
            patEOVO.executeQuery();
        }
        final OAViewObject vo1 = (OAViewObject)am.findViewObject("MortalityScreeningQuesFrstVO1");
        if (vo1 != null) {
            vo1.setWhereClause((String)null);
            vo1.executeQuery();
        }
        final OAViewObject vo2 = (OAViewObject)am.findViewObject("MortalityScreeningQuesSecVO1");
        if (vo2 != null) {
            vo2.setWhereClause((String)null);
            vo2.executeQuery();
        }
    }
    
    private void executeHarmScreen(final OAApplicationModule am) {
        final OAViewObject patEOVO = (OAViewObject)am.findViewObject("HarmScreeningEOVO2");
        if (patEOVO != null) {
            patEOVO.setWhereClause((String)null);
            patEOVO.setWhereClause("REQUEST_ID= '" + this.reqid.trim() + "'");
            patEOVO.executeQuery();
        }
    }
    
    private void disableCB(final OAApplicationModule am) {
        final OAViewObject disVO = (OAViewObject)am.findViewObject("xxReviewerCBVO1");
        if (disVO != null) {
            disVO.executeQuery();
            final xxReviewerCBVORowImpl row1 = (xxReviewerCBVORowImpl)disVO.createRow();
            disVO.insertRow((Row)row1);
            row1.setNewRowState((byte)(-1));
        }
    }
    
    private void disableCBGrp1(final OAApplicationModule am) {
        final OAViewObject disVO = (OAViewObject)am.findViewObject("xxReviewerCBVO1");
        if (disVO != null) {
            final xxReviewerCBVORowImpl row1 = (xxReviewerCBVORowImpl)disVO.getCurrentRow();
            if (row1 != null) {
                row1.setMRP(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.setNAR(Boolean.valueOf((boolean)Boolean.FALSE));
            }
        }
    }
    
    private void disableCBGrp2(final OAApplicationModule am) {
        final OAViewObject disVO = (OAViewObject)am.findViewObject("xxReviewerCBVO1");
        if (disVO != null) {
            final xxReviewerCBVORowImpl row1 = (xxReviewerCBVORowImpl)disVO.getCurrentRow();
            if (row1 != null) {
                row1.setMRP(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setNAR(Boolean.valueOf((boolean)Boolean.TRUE));
            }
        }
    }
    
    private void enableCB(final OAApplicationModule am) {
        final OAViewObject disVO = (OAViewObject)am.findViewObject("xxReviewerCBVO1");
        if (disVO != null) {
            final xxReviewerCBVORowImpl row1 = (xxReviewerCBVORowImpl)disVO.getCurrentRow();
            if (row1 != null) {
                row1.setMRP(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setNAR(Boolean.valueOf((boolean)Boolean.FALSE));
            }
        }
    }
    
    private void validateImprovementProject(final OAPageContext pageContext, final OAWebBean webBean) {
        final OAMessageLovInputBean projlead = (OAMessageLovInputBean)webBean.findChildRecursive("ProjectLeadLOV");
        final OAMessageTextInputBean projName = (OAMessageTextInputBean)webBean.findChildRecursive("ProjNameTxt");
        final OAMessageTextInputBean projDesc = (OAMessageTextInputBean)webBean.findChildRecursive("ProjDescTxt");
        final ArrayList errList = new ArrayList();
        if (projName != null) {
            final String projNameValue = (String)projName.getValue(pageContext);
            if (projNameValue == null || projNameValue.equals("")) {
                throw new OAException("Project name field is required.");
            }
        }
        if (projDesc != null) {
            final String projDescValue = (String)projDesc.getValue(pageContext);
            if (projDescValue == null || projDescValue.equals("")) {
                throw new OAException(" Project Desc field is required.");
            }
        }
    }
    
    private void hideReviewer(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject spellVo1 = (OAViewObject)am.findViewObject("xxCommitteeSpellVO1");
        if (spellVo1 != null) {
            spellVo1.executeQuery();
            final xxCommitteeSpellVORowImpl row1 = (xxCommitteeSpellVORowImpl)spellVo1.createRow();
            spellVo1.insertRow((Row)row1);
            row1.setNewRowState((byte)(-1));
            if (row1 != null) {
                row1.setcommittee(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.setreviewer(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setcomAddreviewer(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setaddButton(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setdptmobcommittee(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.setdptmorbreviewer(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.sethosmobcommittee(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.sethosmorbreviewer(Boolean.valueOf((boolean)Boolean.FALSE));
            }
        }
    }
    
    private void hideCommitiee(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject spellVo1 = (OAViewObject)am.findViewObject("xxCommitteeSpellVO1");
        if (spellVo1 != null) {
            spellVo1.executeQuery();
            final xxCommitteeSpellVORowImpl row1 = (xxCommitteeSpellVORowImpl)spellVo1.createRow();
            spellVo1.insertRow((Row)row1);
            row1.setNewRowState((byte)(-1));
            if (row1 != null) {
                row1.setcommittee(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setreviewer(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.setcomAddreviewer(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.setaddButton(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.setdptmobcommittee(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.setdptmorbreviewer(Boolean.valueOf((boolean)Boolean.TRUE));
                row1.sethosmobcommittee(Boolean.valueOf((boolean)Boolean.FALSE));
                row1.sethosmorbreviewer(Boolean.valueOf((boolean)Boolean.TRUE));
            }
        }
    }
    
    private void callTheProceHospitalReviewer(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("Begin SANG_TEST_PROC(:1,:2); end;", -1);
        try {
            oraclecallablestatement.setInt(1, Integer.parseInt(this.ntfID));
            oraclecallablestatement.registerOutParameter(2, 12);
            oraclecallablestatement.execute();
            System.out.println("callTheProceHospitalReviewer 1780 :> " + oraclecallablestatement.getString(2));
        }
        catch (Exception e) {
            throw new OAException("Error in Executing HOSP Rev  procedure :" + e);
        }
    }
    
    private String callTheProceDepartmentReviewer(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_COMMITEE_CHAIR_DEPT_REV_FYI(:1); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid.trim());
            oraclecallablestatement.execute();
            return null;
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating Dept  Procedure:" + e);
        }
    }
    
    private void createRowForCommitieeReviwer(final OAPageContext pageContext, final OAWebBean webBean, final OARow currrow, final String managementcode, final String cbval, final String mortmorbType) {
        final OAApplicationModule am = pageContext.getApplicationModule(webBean);
        final OAViewObject XXApproverVO = (OAViewObject)am.findViewObject("DeptHospReviewerEOVO1");
        final OAViewObject maineovo = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
        ApproverFormSelectManagementEOVORowImpl eorow = null;
        if (maineovo == null) {
            throw new OAException("maineovo is null ");
        }
        maineovo.setWhereClause((String)null);
        maineovo.setWhereClauseParams((Object[])null);
        final String mangeid = pageContext.getParameter("RequestID").trim() + managementcode.trim();
        maineovo.setWhereClause("MANAGEMENT_ID= '" + mangeid + "'");
        eorow = (ApproverFormSelectManagementEOVORowImpl)maineovo.getCurrentRow();
        if (eorow == null) {
            throw new OAException("row is null ");
        }
        if (XXApproverVO != null) {
            XXApproverVO.setWhereClause((String)null);
            final OARow XXRow = (OARow)XXApproverVO.createRow();
            XXRow.setNewRowState((byte)(-1));
            try {
                XXRow.setAttribute("RequestId", (Object)pageContext.getParameter("RequestID"));
            }
            catch (Exception e) {
                throw new OAException("error in yes no " + e.getMessage());
            }
            if (mortmorbType != null) {
                XXRow.setAttribute("Attribute8", (Object)mortmorbType);
            }
            XXRow.setAttribute("TransactionId", (Object)eorow.getTransactionId());
            XXRow.setAttribute("ReviewerName", currrow.getAttribute("ReviewerTransient"));
            XXRow.setAttribute("ManagementId", (Object)(pageContext.getParameter("RequestID") + managementcode));
            XXRow.setAttribute("ManagementName", (Object)managementcode);
            XXRow.setAttribute("CreatedBy", (Object)pageContext.getUserName());
            XXRow.setAttribute("Attribute3", currrow.getAttribute("empid"));
            XXRow.setAttribute("Attribute1", (Object)"In-Progress");
            XXRow.setAttribute("Attribute2", (Object)"Reviewer");
            XXApproverVO.insertRow((Row)XXRow);
            return;
        }
        throw new OAException("XXApproverVO is null");
    }
    
    private void hideactionTables(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject actionspellVo = (OAViewObject)am.findViewObject("xxActionSpellVO1");
        if (actionspellVo != null) {
            actionspellVo.executeQuery();
            final xxActionSpellVORowImpl actionspellrow = (xxActionSpellVORowImpl)actionspellVo.createRow();
            actionspellVo.insertRow((Row)actionspellrow);
            actionspellrow.setNewRowState((byte)(-1));
            if (actionspellrow != null) {
                actionspellrow.setmrptable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setmngtable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setdeptable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.sethostable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setrcatable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setaartable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setstrtable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setimptable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setnartable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.setdepmorbtable(Boolean.valueOf((boolean)Boolean.FALSE));
                actionspellrow.sethosmorbtable(Boolean.valueOf((boolean)Boolean.FALSE));
            }
        }
    }
    
    private void showactionTables(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject actionspellVo = (OAViewObject)am.findViewObject("xxActionSpellVO1");
        if (actionspellVo != null) {
            final xxActionSpellVORowImpl actionspellrow = (xxActionSpellVORowImpl)actionspellVo.getCurrentRow();
            if (actionspellrow != null) {
                if (this.CB1) {
                    actionspellrow.setmrptable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB2) {
                    actionspellrow.setmngtable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB3 && !"commitee".equalsIgnoreCase(pageContext.getParameter("reviewer"))) {
                    actionspellrow.setdeptable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB4 && !"commitee".equalsIgnoreCase(pageContext.getParameter("reviewer"))) {
                    System.out.println("CB 4 called in spell: " + this.CB4);
                    actionspellrow.sethostable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB5) {
                    actionspellrow.setrcatable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB6) {
                    actionspellrow.setaartable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB7) {
                    actionspellrow.setstrtable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB8) {
                    actionspellrow.setimptable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB9) {
                    actionspellrow.setnartable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB10 && !"commitee".equalsIgnoreCase(pageContext.getParameter("reviewer"))) {
                    System.out.println("CB 10 called in spell: " + this.CB10);
                    actionspellrow.setdepmorbtable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
                if (this.CB11 && !"commitee".equalsIgnoreCase(pageContext.getParameter("reviewer"))) {
                    System.out.println("CB 11 called in spell: " + this.CB11);
                    actionspellrow.sethosmorbtable(Boolean.valueOf((boolean)Boolean.TRUE));
                }
            }
        }
    }
    
    private void showPartiallyactionTables(final OAApplicationModule am) {
        final OAViewObject actionspellVo = (OAViewObject)am.findViewObject("xxActionSpellVO1");
        if (actionspellVo != null) {
            final xxActionSpellVORowImpl actionspellrow = (xxActionSpellVORowImpl)actionspellVo.getCurrentRow();
            if (actionspellrow != null) {
                if (!this.CB1) {
                    actionspellrow.setmrptable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB2) {
                    actionspellrow.setmngtable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB3) {
                    actionspellrow.setdeptable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB10) {
                    actionspellrow.setdepmorbtable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB11) {
                    actionspellrow.sethosmorbtable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB4) {
                    actionspellrow.sethostable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB5) {
                    actionspellrow.setrcatable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB6) {
                    actionspellrow.setaartable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB7) {
                    actionspellrow.setstrtable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB8) {
                    actionspellrow.setimptable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                if (!this.CB9) {
                    actionspellrow.setnartable(Boolean.valueOf((boolean)Boolean.FALSE));
                }
            }
        }
    }
    
    private void hideCommitteeChairRows(final OAApplicationModule am) {
        final OAViewObject CommispellVo = (OAViewObject)am.findViewObject("xxActionCommiSpellVO1");
        if (CommispellVo != null) {
            CommispellVo.executeQuery();
            final xxActionCommiSpellVORowImpl Commispellrow = (xxActionCommiSpellVORowImpl)CommispellVo.createRow();
            CommispellVo.insertRow((Row)Commispellrow);
            Commispellrow.setNewRowState((byte)(-1));
            if (Commispellrow != null) {
                if ("DPT".equals(this.dept)) {
                    Commispellrow.setdepRow(Boolean.valueOf((boolean)Boolean.TRUE));
                    Commispellrow.setmrpRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setmngRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.sethosRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setrcaRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setaarRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setstrRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setimpRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setnarRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.sethosmorbRow(Boolean.valueOf((boolean)Boolean.FALSE));
                }
                else if ("HOS".equals(this.dept)) {
                    Commispellrow.setmrpRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setmngRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setdepRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setrcaRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setaarRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setstrRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setimpRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    Commispellrow.setnarRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    System.out.println(" >> DptName in Approver Form is >> : " + this.DptName);
                    if (this.mortMorb != null && "MORT".equalsIgnoreCase(this.mortMorb.trim())) {
                        System.out.println("1 DptName in Approver Form is >> : " + this.mortMorb);
                        Commispellrow.sethosRow(Boolean.valueOf((boolean)Boolean.TRUE));
                        Commispellrow.sethosmorbRow(Boolean.valueOf((boolean)Boolean.FALSE));
                    }
                    else if (this.mortMorb != null && "MORB".equalsIgnoreCase(this.mortMorb.trim())) {
                        System.out.println("2 DptName in Approver Form is >> : " + this.mortMorb);
                        Commispellrow.sethosRow(Boolean.valueOf((boolean)Boolean.FALSE));
                        Commispellrow.sethosmorbRow(Boolean.valueOf((boolean)Boolean.TRUE));
                    }
                }
            }
        }
    }
    
    private void callTheProceImprovementProject(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_IMPROVEMENT_PROJECT(:1); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid.trim());
            oraclecallablestatement.execute();
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating Imp Proj  Procedure:" + e);
        }
    }
    
    private void callTheProceSpecialTaskForce(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_COMMITEE_CHAIR_REQ_SUP(:1,:2); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid.trim());
            oraclecallablestatement.setString(2, "STF");
            oraclecallablestatement.execute();
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating Spl task force Procedure:" + e);
        }
    }
    
    private void loadData(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean, final String reqid) {
        final OAViewObject maineovo = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
        if (maineovo != null) {
            maineovo.setWhereClause((String)null);
            maineovo.setWhereClauseParams((Object[])null);
            maineovo.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'");
            maineovo.executeQuery();
            if (maineovo.getRowCount() > 0) {
                final ApproverFormSelectManagementEOVORowImpl MainRow = (ApproverFormSelectManagementEOVORowImpl)maineovo.first();
                if (MainRow != null) {
                    final OAMessageLovInputBean ProjectLeadLOV = (OAMessageLovInputBean)webBean.findChildRecursive("ProjectLeadLOV");
                    final OAMessageTextInputBean ProjectName = (OAMessageTextInputBean)webBean.findChildRecursive("ProjNameTxt");
                    final OAMessageTextInputBean ProjectDesc = (OAMessageTextInputBean)webBean.findChildRecursive("ProjDescTxt");
                    if (ProjectLeadLOV != null && MainRow.getProjectLead() != null) {
                        ProjectLeadLOV.setValue(pageContext, (Object)MainRow.getProjectLead());
                        this.CB8 = Boolean.TRUE;
                        this.showactionTables(am, pageContext);
                    }
                    if (ProjectName != null && MainRow.getProjectName() != null) {
                        ProjectName.setValue(pageContext, (Object)MainRow.getProjectName());
                    }
                    if (ProjectDesc != null && MainRow.getProjectDescription() != null) {
                        ProjectDesc.setValue(pageContext, (Object)MainRow.getProjectDescription());
                    }
                }
                else {
                    System.out.println("ApproverFormSelectManagementEOVO1 row null");
                }
            }
            else {
                am.invokeMethod("createApproverFormSelectManagement");
            }
        }
        final OAViewObject MRPReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO1");
        if (MRPReviewer != null) {
            MRPReviewer.setWhereClause((String)null);
            MRPReviewer.setWhereClauseParams((Object[])null);
            MRPReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "MRP" + "'");
            MRPReviewer.executeQuery();
            if (MRPReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb1 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox1");
                if (cb1 != null) {
                    cb1.setChecked(true);
                    this.CB1 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.mrp = Boolean.TRUE;
                }
            }
            while (MRPReviewer.hasNext()) {
                final OARow MRPReviewerRow = (OARow)MRPReviewer.next();
                if (MRPReviewerRow != null) {
                    final String nstatus = this.getNotificationStatus(am, pageContext, webBean, "MRP", "" + MRPReviewerRow.getAttribute("EmployeeId"), "");
                    MRPReviewerRow.setAttribute("NotificationStatus", (Object)nstatus);
                    MRPReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                }
            }
        }
        final OAViewObject MNGReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO2");
        if (MNGReviewer != null) {
            MNGReviewer.setWhereClause((String)null);
            MNGReviewer.setWhereClauseParams((Object[])null);
            MNGReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "MNG" + "'");
            MNGReviewer.executeQuery();
            if (MNGReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb2 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox2");
                if (cb2 != null) {
                    cb2.setChecked(true);
                    this.CB2 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.mng = Boolean.TRUE;
                }
            }
            while (MNGReviewer.hasNext()) {
                final OARow MNGReviewerRow = (OARow)MNGReviewer.next();
                if (MNGReviewerRow != null) {
                    final String nstatus2 = this.getNotificationStatus(am, pageContext, webBean, "MNG", "" + MNGReviewerRow.getAttribute("EmployeeId"), "");
                    MNGReviewerRow.setAttribute("NotificationStatus", (Object)nstatus2);
                    MNGReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                }
            }
        }
        final OAViewObject DPTReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO3");
        if (DPTReviewer != null) {
            DPTReviewer.setWhereClause((String)null);
            DPTReviewer.setWhereClauseParams((Object[])null);
            DPTReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "DPT" + "'");
            DPTReviewer.executeQuery();
            if (DPTReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb3 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox3");
                if (cb3 != null) {
                    cb3.setChecked(true);
                    this.CB3 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.dep = Boolean.TRUE;
                }
                while (DPTReviewer.hasNext()) {
                    final OARow DPTReviewerRow = (OARow)DPTReviewer.next();
                    if (DPTReviewerRow != null) {
                        final String nstatus3 = this.getNotificationStatus(am, pageContext, webBean, "DPT", "" + DPTReviewerRow.getAttribute("EmployeeId"), "" + DPTReviewerRow.getAttribute("DepartmentMorb"));
                        DPTReviewerRow.setAttribute("NotificationStatus", (Object)nstatus3);
                        DPTReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                    }
                }
            }
        }
        final OAViewObject HOSReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO4");
        if (HOSReviewer != null) {
            HOSReviewer.setWhereClause((String)null);
            HOSReviewer.setWhereClauseParams((Object[])null);
            HOSReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "HOS" + "'" + " AND DEPARTMENT_MORB ='" + "Mortality" + "'");
            HOSReviewer.executeQuery();
            if (HOSReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb4 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox4");
                if (cb4 != null) {
                    cb4.setChecked(true);
                    this.CB4 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.hosp = Boolean.TRUE;
                }
                while (HOSReviewer.hasNext()) {
                    final OARow HOSReviewerRow = (OARow)HOSReviewer.next();
                    if (HOSReviewerRow != null) {
                        final String nstatus4 = this.getNotificationStatus(am, pageContext, webBean, "HOS", "" + HOSReviewerRow.getAttribute("EmployeeId"), "Mortality");
                        HOSReviewerRow.setAttribute("NotificationStatus", (Object)nstatus4);
                        HOSReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                    }
                }
            }
        }
        final OAViewObject HOSMorbReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO5");
        if (HOSMorbReviewer != null) {
            HOSMorbReviewer.setWhereClause((String)null);
            HOSMorbReviewer.setWhereClauseParams((Object[])null);
            HOSMorbReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "HOS" + "'" + " AND DEPARTMENT_MORB ='" + "Morbidity" + "'");
            HOSMorbReviewer.executeQuery();
            if (HOSMorbReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb5 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox11");
                if (cb5 != null) {
                    cb5.setChecked(true);
                    this.CB11 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.hosp = Boolean.TRUE;
                }
            }
            while (HOSMorbReviewer.hasNext()) {
                final OARow HOSMorbReviewerRow = (OARow)HOSMorbReviewer.next();
                if (HOSMorbReviewerRow != null) {
                    final String nstatus5 = this.getNotificationStatus(am, pageContext, webBean, "HOS", "" + HOSMorbReviewerRow.getAttribute("EmployeeId"), "Morbidity");
                    HOSMorbReviewerRow.setAttribute("NotificationStatus", (Object)nstatus5);
                    HOSMorbReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                }
            }
        }
        final OAViewObject RCAReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO6");
        if (RCAReviewer != null) {
            RCAReviewer.setWhereClause((String)null);
            RCAReviewer.setWhereClauseParams((Object[])null);
            RCAReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "ROC" + "'");
            RCAReviewer.executeQuery();
            if (RCAReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb6 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox5");
                if (cb6 != null) {
                    cb6.setChecked(true);
                    this.CB5 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.RootCauseAnalysis = Boolean.TRUE;
                }
                while (RCAReviewer.hasNext()) {
                    final OARow RCAReviewerRow = (OARow)RCAReviewer.next();
                    if (RCAReviewerRow != null) {
                        final String nstatus6 = this.getNotificationStatus(am, pageContext, webBean, "ROC", "" + RCAReviewerRow.getAttribute("EmployeeId"), "");
                        RCAReviewerRow.setAttribute("NotificationStatus", (Object)nstatus6);
                        RCAReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                    }
                }
            }
        }
        final OAViewObject AFAReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO7");
        if (AFAReviewer != null) {
            AFAReviewer.setWhereClause((String)null);
            AFAReviewer.setWhereClauseParams((Object[])null);
            AFAReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "AFA" + "'");
            AFAReviewer.executeQuery();
            if (AFAReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb7 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox6");
                if (cb7 != null) {
                    cb7.setChecked(true);
                    this.CB6 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.AfterActReview = Boolean.TRUE;
                }
            }
            while (AFAReviewer.hasNext()) {
                final OARow AFAReviewerRow = (OARow)AFAReviewer.next();
                if (AFAReviewerRow != null) {
                    final String nstatus7 = this.getNotificationStatus(am, pageContext, webBean, "AFA", "" + AFAReviewerRow.getAttribute("EmployeeId"), "");
                    AFAReviewerRow.setAttribute("NotificationStatus", (Object)nstatus7);
                    AFAReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                }
            }
        }
        final OAViewObject STFReviewer = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO8");
        if (STFReviewer != null) {
            STFReviewer.setWhereClause((String)null);
            STFReviewer.setWhereClauseParams((Object[])null);
            STFReviewer.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'" + " AND MANAGEMENT_ID = '" + this.reqid + "STF" + "'");
            STFReviewer.executeQuery();
            if (STFReviewer.getRowCount() > 0) {
                final OAMessageCheckBoxBean cb8 = (OAMessageCheckBoxBean)webBean.findChildRecursive("checkbox7");
                if (cb8 != null) {
                    cb8.setChecked(true);
                    this.CB7 = Boolean.TRUE;
                    this.showactionTables(am, pageContext);
                    this.splTaskForce = Boolean.TRUE;
                }
            }
            while (STFReviewer.hasNext()) {
                final OARow STFReviewerRow = (OARow)STFReviewer.next();
                if (STFReviewerRow != null) {
                    final String nstatus8 = this.getNotificationStatus(am, pageContext, webBean, "STF", "" + STFReviewerRow.getAttribute("EmployeeId"), "" + STFReviewerRow.getAttribute("Attribute8"));
                    STFReviewerRow.setAttribute("NotificationStatus", (Object)nstatus8);
                    STFReviewerRow.setAttribute("PreviouslyAdded", (Object)(boolean)Boolean.TRUE);
                }
            }
        }
    }
    
    public void callProcedures(final OAApplicationModule am, final OAPageContext pageContext) {
        if (this.mrp) {
            this.callTheProcemrp(pageContext, am);
            System.out.println("Action page Procedure Called : >> MRP > " + this.mrp);
        }
        if (this.mng) {
            this.callTheProcemng(pageContext, am);
            System.out.println("Action page Procedure Called : >> MNG > " + this.mng);
        }
        if (this.dep || this.depmorb) {
            this.callTheProceDepartment(pageContext, am);
            System.out.println("Action page Procedure Called : >> DPT > " + this.dep + this.depmorb);
        }
        if (this.hosp || this.hospmorb) {
            this.callTheProceHospital(pageContext, am);
            System.out.println("Action page Procedure Called : >> HOSP > " + this.hosp + this.hospmorb);
        }
        if (this.hospreviewer && this.ntfID != null) {
            this.callTheProceHospitalReviewer(pageContext, am);
            System.out.println("Action page Procedure Called : >> HOSP Rev > " + this.hospreviewer);
        }
        if (this.depreviwer) {
            this.callTheProceDepartmentReviewer(pageContext, am);
            System.out.println("Action page Procedure Called : >> DPT Rev > " + this.depreviwer);
        }
        if (this.splTaskForce) {
            this.callTheProceSpecialTaskForce(pageContext, am);
            System.out.println("Action page Procedure Called : >> STF > " + this.splTaskForce);
        }
        if (this.ImpProj) {
            this.callTheProceImprovementProject(pageContext, am);
            System.out.println("Action page Procedure Called : >> IMP > " + this.ImpProj);
        }
        if (this.AfterActReview) {
            this.callTheProceAfterActionReview(pageContext, am);
            System.out.println("Action page Procedure Called : >> AFA > " + this.AfterActReview);
        }
        if (this.RootCauseAnalysis) {
            this.callTheProceRootCauseAnalysis(pageContext, am);
            System.out.println("Action page Procedure Called : >> RCA > " + this.RootCauseAnalysis);
        }
        if (this.mrp || this.mng || this.dep || this.hosp || this.depreviwer || this.hospreviewer || this.ImpProj || this.splTaskForce || this.AfterActReview || this.RootCauseAnalysis || this.depmorb || this.hospmorb) {
            final OAException message = new OAException("Email has been sent to reviewers for the Request id : " + this.reqid);
            final OADialogPage dialogPage = new OADialogPage((byte)3, message, (OAException)null, "", (String)null);
            dialogPage.setOkButtonToPost(true);
            dialogPage.setOkButtonItemName("okay");
            dialogPage.setOkButtonLabel("Ok");
            dialogPage.setPostToCallingPage(true);
            pageContext.redirectToDialogPage(dialogPage);
        }
        else if (this.noActionRequired) {
            final OAException message2 = new OAException("Request has been closed for the Request id : " + this.reqid);
            final OADialogPage dialogPage2 = new OADialogPage((byte)3, message2, (OAException)null, "", (String)null);
            dialogPage2.setOkButtonToPost(true);
            dialogPage2.setOkButtonItemName("okayC");
            dialogPage2.setOkButtonLabel("Ok");
            dialogPage2.setPostToCallingPage(true);
            pageContext.redirectToDialogPage(dialogPage2);
        }
    }
    
    private void callTheProceAfterActionReview(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_COMMITEE_CHAIR_REQ_SUP(:1,:2); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid.trim());
            oraclecallablestatement.setString(2, "AFA");
            oraclecallablestatement.execute();
            System.out.println("approver form after action review procedure called ");
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating After Action Review Procedure:" + e);
        }
    }
    
    private void callTheProceRootCauseAnalysis(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_MORTALITY_COMMITEE_CHAIR_REQ_SUP(:1,:2); end;", -1);
        try {
            oraclecallablestatement.setString(1, this.reqid.trim());
            oraclecallablestatement.setString(2, "ROC");
            oraclecallablestatement.execute();
            System.out.println("Root causes analysis procedure called ");
        }
        catch (Exception e) {
            throw new OAException("Error in Excuating Root causes analysis  Procedure:" + e);
        }
    }
    
    private void executeReviewerVO(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject VO = (OAViewObject)am.findViewObject("ApproverFormSelectManagementVO1");
        if (VO != null) {
            VO.setWhereClause((String)null);
            VO.setWhereClauseParams((Object[])null);
            VO.executeQuery();
            System.out.println("vo executed");
            return;
        }
        throw new OAException("ApproverFormSelectManagementVO1 is null");
    }
    
    private void CretaeReviewButton(final OAWebBean webBean, final OAApplicationModule am, final OAPageContext pageContext) {
        final OACellFormatBean MrpFormColumn = (OACellFormatBean)webBean.findChildRecursive("MrpFormColumn");
        final OAButtonBean MrpFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (MrpFormbtn != null) {
            MrpFormbtn.setID("MrpFormbtn");
            MrpFormbtn.setText("MRP Review form");
            MrpFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormPatientComplaintPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "MRP");
            if (MrpFormColumn != null) {
                MrpFormColumn.addIndexedChild((UINode)MrpFormbtn);
            }
        }
        final OACellFormatBean MngFormColumn = (OACellFormatBean)webBean.findChildRecursive("MngFormColumn");
        final OAButtonBean MngFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (MngFormbtn != null) {
            MngFormbtn.setID("MngFormbtn");
            MngFormbtn.setText("Manager / Chairman Review Form");
            MngFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormChairmanReviewNoPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "MNG");
            if (MngFormbtn != null) {
                MngFormColumn.addIndexedChild((UINode)MngFormbtn);
            }
        }
        final OACellFormatBean DptMortFormColumn = (OACellFormatBean)webBean.findChildRecursive("DptMortFormColumn");
        final OAButtonBean DptMortFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (DptMortFormbtn != null) {
            DptMortFormbtn.setID("DptMortFormbtn");
            DptMortFormbtn.setText("Department Mortality form");
            DptMortFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/DepartmentMortalityPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "DPT");
            if (DptMortFormColumn != null) {
                DptMortFormColumn.addIndexedChild((UINode)DptMortFormbtn);
            }
        }
        final OACellFormatBean DptMorbFormColumn = (OACellFormatBean)webBean.findChildRecursive("DptMorbFormColumn");
        final OAButtonBean DptMorbFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (DptMorbFormbtn != null) {
            DptMorbFormbtn.setID("DptMorbFormbtn");
            DptMorbFormbtn.setText("Department Morbidity form");
            DptMorbFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/DepartmentMorbidityPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "MNG");
            if (DptMorbFormColumn != null) {
                DptMorbFormColumn.addIndexedChild((UINode)DptMorbFormbtn);
            }
        }
        final OACellFormatBean HosMortFormColumn = (OACellFormatBean)webBean.findChildRecursive("HosMortFormColumn");
        final OAButtonBean HosMortFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (HosMortFormbtn != null) {
            HosMortFormbtn.setID("DptMortFormbtn");
            HosMortFormbtn.setText("Hospital Mortality form");
            HosMortFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/morbidity/webui/HospitalMorbidityReviewPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "HOS");
            if (HosMortFormColumn != null) {
                HosMortFormColumn.addIndexedChild((UINode)HosMortFormbtn);
            }
        }
        final OACellFormatBean HosMorbFormColumn = (OACellFormatBean)webBean.findChildRecursive("HosMorbFormColumn");
        final OAButtonBean HosMorbFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (HosMorbFormbtn != null) {
            HosMorbFormbtn.setID("NewSubmitButton");
            HosMorbFormbtn.setText("Hospital Morbidity form");
            HosMorbFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/morbidity/webui/HospitalMorbidityReviewPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "HOS");
            if (HosMorbFormColumn != null) {
                HosMorbFormColumn.addIndexedChild((UINode)HosMorbFormbtn);
            }
        }
        final OACellFormatBean RcaFormColumn = (OACellFormatBean)webBean.findChildRecursive("RcaFormColumn");
        final OAButtonBean RcaFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (RcaFormbtn != null) {
            RcaFormbtn.setID("NewSubmitButton");
            RcaFormbtn.setText("Root Cause Analysis form");
            RcaFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/RootCauseAnalysisPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "RCA");
            System.out.println("Approver form line 2217 >> " + this.reqid + " table type:  " + this.formtype);
            if (RcaFormColumn != null) {
                RcaFormColumn.addIndexedChild((UINode)RcaFormbtn);
            }
        }
        final OACellFormatBean AfaFormColumn = (OACellFormatBean)webBean.findChildRecursive("AfaFormColumn");
        final OAButtonBean AfaFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (AfaFormbtn != null) {
            AfaFormbtn.setID("NewSubmitButton");
            AfaFormbtn.setText("After Action Review Form form");
            AfaFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/afterActionReview/webui/AfterActionReviewPG&RequestID=" + pageContext.getParameter("RequestID") + "&tableType=" + pageContext.getParameter("tableType") + "&dept=" + "AFA" + "&ParentRole=" + "Edit");
            if (AfaFormColumn != null) {
                AfaFormColumn.addIndexedChild((UINode)AfaFormbtn);
            }
        }
        final OACellFormatBean StfFormColumn = (OACellFormatBean)webBean.findChildRecursive("StfFormColumn");
        final OAButtonBean StfFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (StfFormbtn != null) {
            StfFormbtn.setID("NewSubmitButton");
            StfFormbtn.setText("Special Task Force form");
            StfFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/SpecialTaskForceMorbidityPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "STF");
            if (StfFormColumn != null) {
                StfFormColumn.addIndexedChild((UINode)StfFormbtn);
            }
        }
        final OACellFormatBean ImpFormColumn = (OACellFormatBean)webBean.findChildRecursive("ImpFormColumn");
        final OAButtonBean ImpFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (ImpFormbtn != null) {
            ImpFormbtn.setID("NewSubmitButton");
            ImpFormbtn.setText("Improvement Project form");
            ImpFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ImprovementProject&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "IMP");
            if (ImpFormColumn != null) {
                ImpFormColumn.addIndexedChild((UINode)ImpFormbtn);
            }
        }
    }
    
    private void checkboxSpelValidation(final OAPageContext pageContext, final OAApplicationModule am) {
        if ("on".equals(pageContext.getParameter("checkbox1"))) {
            this.CB1 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB1 = Boolean.FALSE;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox2"))) {
            this.CB2 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB2 = Boolean.FALSE;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox3"))) {
            this.CB3 = Boolean.TRUE;
            this.mortMorb = "Mortality";
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB3 = Boolean.FALSE;
            this.mortMorb = null;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox10"))) {
            this.CB10 = Boolean.TRUE;
            this.mortMorb = "Morbidity";
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB10 = Boolean.FALSE;
            this.mortMorb = null;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox4"))) {
            this.CB4 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
            this.mortMorb = "Mortality";
        }
        else {
            this.CB4 = Boolean.FALSE;
            this.mortMorb = null;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox11"))) {
            this.CB11 = Boolean.TRUE;
            this.mortMorb = "Morbidity";
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB11 = Boolean.FALSE;
            this.mortMorb = null;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox5"))) {
            this.CB5 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB5 = Boolean.FALSE;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox6"))) {
            this.CB6 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB6 = Boolean.FALSE;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox7"))) {
            this.CB7 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB7 = Boolean.FALSE;
            this.showPartiallyactionTables(am);
        }
        if ("on".equals(pageContext.getParameter("checkbox8"))) {
            this.CB8 = Boolean.TRUE;
            this.showactionTables(am, pageContext);
        }
        else {
            this.CB8 = Boolean.FALSE;
            this.showPartiallyactionTables(am);
        }
    }
    
    private void CretaeReviewButton1(final OAWebBean webBean, final OAApplicationModule am, final OAPageContext pageContext) {
        final OACellFormatBean MrpFormColumn = (OACellFormatBean)webBean.findChildRecursive("MrpFormColumn");
        final OAButtonBean MrpFormbtn = (OAButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext, "BUTTON");
        if (MrpFormbtn != null) {
            MrpFormbtn.setID("MrpFormbtn");
            MrpFormbtn.setText("MRP Review form");
            MrpFormbtn.setDestination("OA.jsp?page=/ngha/oracle/apps/inv/mortality/webui/ApproverFormPatientComplaintPG&RequestID=" + this.reqid + "&tableType=" + this.formtype + "&ActionPage=" + "Yes" + "&dept=" + "MRP");
            MrpFormbtn.setTargetFrame("_blank");
            if (MrpFormColumn != null) {
                MrpFormColumn.addIndexedChild((UINode)MrpFormbtn);
            }
        }
    }
    
    private void loadExistingdata(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
        if (CurrentEOVO != null) {
            CurrentEOVO.setWhereClause((String)null);
            CurrentEOVO.setWhereClauseParams((Object[])null);
            CurrentEOVO.setWhereClause(" REQUEST_ID = '" + this.reqid + "'");
            CurrentEOVO.executeQuery();
            if (CurrentEOVO.getRowCount() != 0 && CurrentEOVO.getRowCount() > 0) {
                final ApproverFormSelectManagementEOVORowImpl EovoRow = (ApproverFormSelectManagementEOVORowImpl)CurrentEOVO.first();
                if (EovoRow == null) {
                    throw new OAException(" ApproverFormSelectManagementEOVO1 row is null");
                }
            }
            else {
                this.hideactionTables(am, pageContext);
            }
            return;
        }
        throw new OAException("MRPReviewPatientComplaintEOVO1 is null ");
    }
    
    private void createNewMRPRow(final OAApplicationModule am, final OAPageContext pageContext) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO1");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "MRP");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("MRP");
        Row.setAttribute7("Reviewer");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.mrp = Boolean.TRUE;
    }
    
    private void createNewMNGRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO2");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "MNG");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        final OAMessageChoiceBean ManagerYesNo = (OAMessageChoiceBean)webBean.findChildRecursive("YesrNoMC");
        if (ManagerYesNo != null) {
            Row.setAttribute6("" + ManagerYesNo.getValue(pageContext));
        }
        Row.setManagementName("MNG");
        Row.setAttribute7("Reviewer");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.mng = Boolean.TRUE;
    }
    
    private void createNewHosMortReviewersRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("DeptHospReviewerEOVO2");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final DeptHospReviewerEOVORowImpl Row = (DeptHospReviewerEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "HOS");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreatedBy(pageContext.getUserName());
        }
        Row.setManagementName("HOS");
        Row.setAttribute3("Mortality");
        Row.setAttribute2("Reviewer");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.depreviwer = Boolean.TRUE;
    }
    
    private void createNewHOSMorbReviewersRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("DeptHospReviewerEOVO3");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final DeptHospReviewerEOVORowImpl Row = (DeptHospReviewerEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "HOS");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreatedBy(pageContext.getUserName());
        }
        Row.setManagementName("HOS");
        Row.setAttribute3("Morbidity");
        Row.setAttribute2("Reviewer");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.depreviwer = Boolean.TRUE;
    }
    
    private void createNewDPTReviewersRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("DeptHospReviewerEOVO1");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final DeptHospReviewerEOVORowImpl Row = (DeptHospReviewerEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            final OAMessageChoiceBean deptBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMC");
            final OAMessageChoiceBean mortMorbBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMorbMC");
            System.out.println("Mort Morb in Appprover form : >>> " + mortMorbBean.getValue(pageContext));
            System.out.println("depart in Appprover form : >>> " + deptBean.getValue(pageContext));
            if (deptBean == null || mortMorbBean != null) {}
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "DPT");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreatedBy(pageContext.getUserName());
        }
        Row.setManagementName("DPT");
        Row.setAttribute2("Reviewer");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.depreviwer = Boolean.TRUE;
    }
    
    private void createNewDPTRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO3");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            final OAMessageChoiceBean deptBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMC");
            final OAMessageChoiceBean mortMorbBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMorbMC");
            System.out.println("Mort Morb in Appprover form : >>> " + mortMorbBean.getValue(pageContext));
            System.out.println("depart in Appprover form : >>> " + deptBean.getValue(pageContext));
            if (deptBean == null || mortMorbBean == null) {
                throw new OAException(" deptBean " + deptBean.getValue(pageContext) + "mortMorbBean " + mortMorbBean.getValue(pageContext));
            }
            Row.setDepartmentMort("" + deptBean.getValue(pageContext));
            Row.setDepartmentMorb("" + mortMorbBean.getValue(pageContext));
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "DPT");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("DPT");
        Row.setAttribute7("Comittiee Chair");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.dep = Boolean.TRUE;
        final OAImageBean addbuttonbean = (OAImageBean)webBean.findChildRecursive("AddRowDPTNew");
        if (CurrentEOVO.getRowCount() > 0) {
            if (addbuttonbean != null) {
                addbuttonbean.setRendered(false);
            }
        }
        else if (addbuttonbean != null) {
            addbuttonbean.setRendered(true);
        }
    }
    
    private void createNewHOSRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO4");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "HOS");
        }
        Row.setDepartmentMorb("Mortality");
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("HOS");
        Row.setAttribute7("Committe Chair");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.hosp = Boolean.TRUE;
        final OAImageBean addbuttonbean = (OAImageBean)webBean.findChildRecursive("AddRowHOSNewCom");
        if (CurrentEOVO.getRowCount() > 0) {
            if (addbuttonbean != null) {
                addbuttonbean.setRendered(false);
            }
        }
        else if (addbuttonbean != null) {
            addbuttonbean.setRendered(true);
        }
    }
    
    private void createNewROCRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO6");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "ROC");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("ROC");
        Row.setAttribute7("Reviewer");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.RootCauseAnalysis = Boolean.TRUE;
        final OAImageBean addbuttonbean = (OAImageBean)webBean.findChildRecursive("AddRowROCNew");
        if (CurrentEOVO.getRowCount() > 0) {
            if (addbuttonbean != null) {
                addbuttonbean.setRendered(false);
            }
        }
        else if (addbuttonbean != null) {
            addbuttonbean.setRendered(true);
        }
    }
    
    private void createNewAFARow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO7");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "AFA");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("AFA");
        Row.setAttribute7("Comiitiee Chair");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.AfterActReview = Boolean.TRUE;
        final OAImageBean addbuttonbean = (OAImageBean)webBean.findChildRecursive("AddRowAFANew");
        if (CurrentEOVO.getRowCount() > 0) {
            if (addbuttonbean != null) {
                addbuttonbean.setRendered(false);
            }
        }
        else if (addbuttonbean != null) {
            addbuttonbean.setRendered(true);
        }
    }
    
    private void createNewSTFRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO8");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "STF");
        }
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("STF");
        Row.setAttribute1("In-Progress");
        Row.setAttribute7("Reviewer");
        final OAMessageChoiceBean mortmorbBean = (OAMessageChoiceBean)webBean.findChildRecursive("mortalityMC1");
        if (mortmorbBean != null) {
            Row.setAttribute8("" + mortmorbBean.getValue(pageContext));
        }
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.splTaskForce = Boolean.TRUE;
        final OAImageBean addbuttonbean = (OAImageBean)webBean.findChildRecursive("AddRowStfNew");
        if (CurrentEOVO.getRowCount() > 0) {
            if (addbuttonbean != null) {
                addbuttonbean.setRendered(false);
            }
        }
        else if (addbuttonbean != null) {
            addbuttonbean.setRendered(true);
        }
    }
    
    private void createNewHOSMorbRow(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject CurrentEOVO = (OAViewObject)am.findViewObject("SangMorApprFrmMrpEOVO5");
        if (!CurrentEOVO.isPreparedForExecution()) {
            CurrentEOVO.setMaxFetchSize(0);
            CurrentEOVO.executeQuery();
        }
        final SangMorApprFrmMrpEOVORowImpl Row = (SangMorApprFrmMrpEOVORowImpl)CurrentEOVO.createRow();
        if (this.reqid != null && !"".equals(this.reqid)) {
            try {
                final Number transid = (Number)am.invokeMethod("getTransactionId");
                Row.setTransactionId(transid);
            }
            catch (Exception e) {
                throw new OAException("error in trans id : " + e.getMessage());
            }
            Row.setRequestId(this.reqid);
            Row.setManagementId(this.reqid + "HOS");
        }
        Row.setDepartmentMorb("Morbidity");
        if (pageContext.getUserName() != null && !"".equals(pageContext.getUserName())) {
            Row.setCreateBy(pageContext.getUserName());
        }
        Row.setManagementName("HOS");
        Row.setAttribute7("Comiitiee Chair");
        Row.setAttribute1("In-Progress");
        CurrentEOVO.insertRow((Row)Row);
        Row.setNewRowState((byte)(-1));
        this.hospmorb = Boolean.TRUE;
        final OAImageBean addbuttonbean = (OAImageBean)webBean.findChildRecursive("AddRowHOSMorbCom");
        if (CurrentEOVO.getRowCount() > 0) {
            if (addbuttonbean != null) {
                addbuttonbean.setRendered(false);
            }
        }
        else if (addbuttonbean != null) {
            addbuttonbean.setRendered(true);
        }
    }
    
    private void setDepartTypeandMortMorbType(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final String query = "select DEPARTMENT_MORT,DEPARTMENT_MORB from SANG_MOR_APPR_FRM_MRP where request_id=:1 and create_by!=:2";
        final Connection conn = pageContext.getApplicationModule(webBean).getOADBTransaction().getJdbcConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, this.reqid);
            stmt.setString(2, pageContext.getUserName());
            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                this.DptMortMorb = rs.getString(1);
                this.DptName = rs.getString(2);
            }
            final OAMessageChoiceBean mortmorbBean = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMorbMC");
            final OAMessageChoiceBean DepartmentName = (OAMessageChoiceBean)webBean.findChildRecursive("DeptMortMC");
            final OAMessageStyledTextBean mortmorbBeanTxt = (OAMessageStyledTextBean)webBean.findChildRecursive("DeptMortMorbStyTxt");
            final OAMessageStyledTextBean DepartmentNameTxt = (OAMessageStyledTextBean)webBean.findChildRecursive("DeptMortStylTxt");
            if (this.DptMortMorb != null && this.DptName != null && mortmorbBean != null && DepartmentName != null) {
                mortmorbBean.setDisabled(true);
                DepartmentName.setDisabled(true);
            }
            if (mortmorbBeanTxt != null) {
                mortmorbBeanTxt.setRendered(true);
                mortmorbBeanTxt.setValue(pageContext, (Object)this.DptMortMorb);
            }
            if (DepartmentNameTxt != null) {
                DepartmentNameTxt.setRendered(true);
                DepartmentNameTxt.setValue(pageContext, (Object)this.DptName);
            }
        }
        catch (Exception e) {
            throw new OAException(" error in prepared statemnet " + e.getMessage());
        }
    }
    
    private void checkMainTableRowCount(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject maineovo = (OAViewObject)am.findViewObject("ApproverFormSelectManagementEOVO1");
        if (maineovo != null) {
            maineovo.setWhereClause((String)null);
            maineovo.setWhereClauseParams((Object[])null);
            maineovo.setWhereClause(" REQUEST_ID = '" + this.reqid.trim() + "'");
            maineovo.executeQuery();
            if (maineovo.getRowCount() == 0) {
                am.invokeMethod("createApproverFormSelectManagement");
            }
        }
    }
    
    private void getDeptAndType(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean) {
        final OAViewObject maineovo = (OAViewObject)am.findViewObject("GetDptnameAndTypeVO1");
        if (maineovo != null) {
            maineovo.setWhereClause((String)null);
            maineovo.setWhereClauseParams((Object[])null);
            maineovo.setWhereClauseParam(0, (Object)this.reqid.trim());
            maineovo.setWhereClauseParam(1, (Object)pageContext.getUserName());
            maineovo.executeQuery();
            final GetDptnameAndTypeVORowImpl row = (GetDptnameAndTypeVORowImpl)maineovo.first();
            if (row != null) {
                pageContext.putDialogMessage(new OAException("getDepartmentMorb val : " + row.getDepartmentMorb()));
                pageContext.putDialogMessage(new OAException("getDepartmentMort val : " + row.getDepartmentMort()));
            }
        }
    }
    
    private String getNotificationStatus(final OAApplicationModule am, final OAPageContext pageContext, final OAWebBean webBean, final String tb, final String empId, final String typ) {
        String query = "";
        if ("MRP".equals(tb) || "MNG".equals(tb)) {
            query = "SELECT DECODE(Wn.Status,'OPEN','PENDING','COMPLETED') STATUS \nFROM Sang_Mor_Appr_Frm_Mrp Sma, Wf_Notifications Wn \nWHERE Sma.Attribute10 = Wn.Notification_Id \nAND Management_Name = '" + tb + "' \n" + "AND Employee_Id = '" + empId + "' \n" + "AND Request_Id = '" + this.reqid + "' ";
        }
        else if ("DPT".equals(tb) || "HOS".equals(tb) || "AFA".equals(tb) || "IMP".equals(tb)) {
            query = "SELECT DECODE(Wn.Status,'OPEN','PENDING','COMPLETED') STATUS \nFROM Sang_Mor_Appr_Frm_Mrp Sma, Wf_Notifications Wn \nWHERE Sma.Attribute5 = Wn.Notification_Id \nAND Management_Name = '" + tb + "' \n" + "AND (DEPARTMENT_MORB = '" + typ + "' OR '" + typ + "' IS NULL) \n" + "AND Employee_Id = '" + empId + "' \n" + "AND Request_Id = '" + this.reqid + "' ";
        }
        else if ("ROC".equals(tb)) {
            query = "SELECT DECODE(Wn.Status,'OPEN','PENDING','COMPLETED') STATUS \nFROM Sang_Mor_Appr_Frm_Mrp Sma, Wf_Notifications Wn \nWHERE Sma.Attribute18 = Wn.Notification_Id \nAND Management_Name = '" + tb + "' \n" + "AND Employee_Id  = '" + empId + "' \n" + "AND Request_Id = '" + this.reqid + "' ";
        }
        else if ("STF".equals(tb)) {
            query = "SELECT DECODE(Wn.Status,'OPEN','PENDING','COMPLETED') STATUS\nFROM Sang_Mor_Appr_Frm_Mrp Sma, Wf_Notifications Wn\nWHERE Sma.Attribute5 = Wn.Notification_Id\nAND Management_Name = '" + tb + "' \n" + "AND (sma.attribute8 = '" + typ + "' OR '" + typ + "' IS NULL) \n" + "AND Employee_Id  = '" + empId + "' \n" + "AND Request_Id = '" + this.reqid + "' ";
        }
        System.out.println("tb ----------------------------------> " + tb);
        final Connection conn = pageContext.getApplicationModule(webBean).getOADBTransaction().getJdbcConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String status = "";
        try {
            stmt = conn.prepareStatement(query, 1);
            rset = stmt.executeQuery();
            if (rset.next()) {
                status = rset.getString(1);
            }
        }
        catch (Exception e) {
            throw new OAException(" error in prepared statemnet for status: " + e.getMessage());
        }
        finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        try {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    public void CloseFYINotification(final OAPageContext pageContext, final OAApplicationModule am) {
        final OADBTransaction oadbtransaction = (OADBTransaction)am.getTransaction();
        final CallableStatement oraclecallablestatement = oadbtransaction.createCallableStatement("begin SANG_MORTALITY_PROCESS_PKG.SANG_CLOSE_FYI_NOTI(:1); end;", -1);
        if (pageContext.getParameter("NtfId") != null && !"".equals(pageContext.getParameter("NtfId"))) {
            try {
                oraclecallablestatement.setString(1, pageContext.getParameter("NtfId"));
                oraclecallablestatement.execute();
                System.out.println("CloseFYINotification called >> " + pageContext.getParameter("NtfId"));
            }
            catch (Exception e) {
                throw new OAException("Error in Executing Close FYI Notification Procedure:" + e);
            }
        }
    }
    
    private void validatefields(final OAPageContext pageContext, final OAApplicationModule am) {
        final String cb1 = pageContext.getParameter("checkbox1");
        final String cb2 = pageContext.getParameter("checkbox2");
        final String cb3 = pageContext.getParameter("checkbox3");
        final String cb4 = pageContext.getParameter("checkbox4");
        final String cb5 = pageContext.getParameter("checkbox5");
        final String cb6 = pageContext.getParameter("checkbox6");
        final String cb7 = pageContext.getParameter("checkbox7");
        final String cb8 = pageContext.getParameter("checkbox8");
        final String cb9 = pageContext.getParameter("checkbox9");
        final String cb10 = pageContext.getParameter("checkbox10");
        final String cb11 = pageContext.getParameter("checkbox11");
        if ("on".equalsIgnoreCase(cb1) || "on".equalsIgnoreCase(cb2) || "on".equalsIgnoreCase(cb3) || "on".equalsIgnoreCase(cb4) || "on".equalsIgnoreCase(cb5) || "on".equalsIgnoreCase(cb6) || "on".equalsIgnoreCase(cb7) || "on".equalsIgnoreCase(cb8) || "on".equalsIgnoreCase(cb9) || "on".equalsIgnoreCase(cb10) || "on".equalsIgnoreCase(cb11)) {
            if ("on".equalsIgnoreCase(cb1)) {
                if (this.rowcount1 == 0) {}
            }
            else if ("on".equalsIgnoreCase(cb2)) {
                if (this.rowcount2 == 0) {}
            }
            else if ("on".equalsIgnoreCase(cb3)) {
                if (this.rowcount3 == 0) {}
            }
            else if (!"on".equalsIgnoreCase(cb7) || this.rowcount7 == 0) {}
        }
    }
    
    static {
        RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header$", "%packagename%");
    }
}
