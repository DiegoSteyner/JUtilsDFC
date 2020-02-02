package jutildfc;

import com.documentum.fc.client.IDfProcess;
import com.documentum.fc.common.DfUtil;

public class Dqls
{
    public static String getSelectTemplateProcess(String processName) throws Exception
    {
        return ("dm_process where object_name = ".concat(DfUtil.toQuotedString(processName)));
    }

    public static String getSelectWorkflowByProcess(String process_id) throws Exception
    {
        return ("select r_object_id from dm_workflow where process_id = ".concat(DfUtil.toQuotedString(process_id)));
    }

    public static String getAtividadeAtual(String workflowId) throws Exception
    {
        return ("SELECT r_act_def_id, r_object_id from dmi_workitem WHERE r_runtime_state in (0,1,3) AND r_workflow_id = ".concat(DfUtil.toQuotedString(workflowId)));
    }

    public static String getProcessVariable(String workflowId, String nomeVariavel) throws Exception
    {
        return ("dmc_wfsd_element where workflow_id = '".concat(workflowId).concat("' and object_name = '").concat(nomeVariavel).concat("'"));
    }

    public static String getFirstActivity(IDfProcess process) throws Exception
    {
        return ("select r_object_id, r_link_dest_act from dm_process where object_name = '".concat(process.getObjectName()).concat("' and any r_link_src_act = 'Initiate' order by r_link_name enable(row_based) "));
    }

    public static String getAllRunningWorkflows() throws Exception
    {
        return ("SELECT w.r_object_id, w.supervisor_name, w.r_start_date, w.object_name AS workflow_name, p.object_name AS process_name FROM dm_workflow w, dm_process p WHERE w.process_id = p.r_object_id AND w.r_runtime_state in (0,1,3)");
    }

    public static String getAllRunningWorkflows(String workflowName) throws Exception
    {
        return ("SELECT w.r_object_id, w.supervisor_name, w.r_start_date, w.object_name AS workflow_name, p.object_name AS process_name FROM dm_workflow w, dm_process p WHERE w.process_id = p.r_object_id AND w.r_runtime_state in (0,1,3) AND p.object_name = ".concat(DfUtil.toQuotedString(workflowName)));
    }

}
