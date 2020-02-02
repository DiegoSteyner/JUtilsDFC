package jutildfc.utilsdfc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.documentum.bpm.IDfActivityEx;
import com.documentum.bpm.IDfProcessEx;
import com.documentum.bpm.IDfProcessVariableMetaData;
import com.documentum.bpm.IDfWorkflowEx;
import com.documentum.bpm.IDfWorkitemEx;
import com.documentum.bpm.sdt.IDfStructuredDataTypeAttribute;
import com.documentum.fc.client.IDfActivity;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPackage;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfProcess;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfWorkflow;
import com.documentum.fc.client.IDfWorkflowBuilder;
import com.documentum.fc.client.IDfWorkitem;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfList;
import com.documentum.fc.common.DfTime;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfList;

import jutildfc.Dqls;
import jutildfc.data.NoteData;
import jutildfc.data.VarsDfc;
import jutildfc.interfaces.AbstractUtilDfc;

public class UidfWorkflow extends AbstractUtilDfc
{
    private static final int IS_SUPPORT_PACKAGE = 1;
    
    public UidfWorkflow(jutildfc.utilsdfc.UidfSession session)
    {
        super(session);
    }

    /**
     * Metodo que retorna um IDfProcess apartir do nome do template
     * 
     * @param s O objeto {@link UidfSession} com a sessão
     * @param processName O nome do processo
     * 
     * @return O objeto {@link IDfProcess}
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfProcess getProcessToTemplate(UidfSession s, String processName) throws Exception
    {
        return (IDfProcess) s.getObjectByQualification(Dqls.getSelectTemplateProcess(processName));
    }

    /**
     * Metodo que retorna o nome de todos os processos instalados
     * 
     * @return O nome e o id de todos os processos instalados
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static String getTemplatesNamesInstalled(UidfSession s) throws Exception
    {
        return (UIdfCollections.getListAtributeValue(s.getInstalledProcess(null), VarsDfc.ATTR_OBJECT_NAME, true).toString());
    }

    /**
     * Metodo que retorna o nome da primeira atividade de um processo
     * 
     * @param s O objeto {@link UidfSession} com a sessão
     * @param process O IDfProcess
     * 
     * @return O nome da primeira atividade
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static String getNameFirstActivity(IDfProcess process) throws Exception
    {
        for (int i = 0; i < process.getActivityCount(); i++)
        {
            if (process.getLinkSrcActivity(i).equals("Initiate"))
            {
                return (process.getLinkDestActivity(i));
            }
        }
        return (null);
    }

    /**
     * Metodo que inicia um Workflow
     * 
     * @param process O Objeto IDfProcess do template
     * @param instanceName O nome que a instancia tera
     * @param objectsToPackage Os Ids a serem adicionados aos packages
     * 
     * 
     * @return O Id do Workflow
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfId startWorkflow(IDfProcess process, String instanceName, List<IDfId> objectsToPackage) throws Exception
    {
        return startWorkflow(process, instanceName, objectsToPackage, null);
    }

    /**
     * Metodo que inicia um Workflow
     * 
     * @param process O Objeto IDfProcess do template
     * @param instanceName O nome que a instancia tera
     * @param objectsToPackage Os Ids a serem adicionados aos packages
     * @param mapProcessVariable As variaveis de processo e os valores
     * 
     * @return O Id do Workflow
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfId startWorkflow(IDfProcess process, String instanceName, List<IDfId> objectsToPackage, Map<String, Object> mapProcessVariable) throws Exception
    {
        return startWorkflow(process, instanceName, objectsToPackage, null, mapProcessVariable);
    }

    /**
     * Metodo que inicia um Workflow
     * 
     * @param process O Objeto IDfProcess do template
     * @param instanceName O nome que a instancia tera
     * @param objectsToPackage Os Ids a serem adicionados aos packages
     * @param listAttachments Os anexos a serem inseridos no workflow
     * @param mapProcessVariable As variaveis de processo e os valores
     * 
     * @return O Id do Workflow
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfId startWorkflow(IDfProcess process, String instanceName, List<IDfId> objectsToPackage, List<IDfId> listAttachments, Map<String, Object> mapProcessVariable) throws Exception
    {
        return startWorkflow(process, instanceName, null, objectsToPackage, listAttachments, mapProcessVariable);
    }

    /**
     * Metodo que inicia um Workflow
     * 
     * @param process O Objeto IDfProcess do template
     * @param instanceName O nome que a instancia tera
     * @param supervisorName O nome do supervisor do fluxo
     * @param objectsToPackage Os Ids a serem adicionados aos packages
     * 
     * @return O Id do Workflow
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfId startWorkflow(IDfProcess process, String instanceName, String supervisorName, List<IDfId> objectsToPackage) throws Exception
    {
        return startWorkflow(process, instanceName, supervisorName, objectsToPackage, null, null);
    }

    /**
     * Metodo que inicia um Workflow
     * 
     * @param process O Objeto IDfProcess do template
     * @param instanceName O nome que a instancia tera
     * @param supervisorName O nome do supervisor do fluxo
     * @param objectsToPackage Os Ids a serem adicionados aos packages
     * @param mapProcessVariable As variaveis de processo e os valores
     * 
     * @return O Id do Workflow
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfId startWorkflow(IDfProcess process, String instanceName, String supervisorName, List<IDfId> objectsToPackage, Map<String, Object> mapProcessVariable) throws Exception
    {
        return startWorkflow(process, instanceName, supervisorName, objectsToPackage, null, mapProcessVariable);
    }

    /**
     * Metodo que inicia um Workflow
     * 
     * @param process O Objeto IDfProcess do template
     * @param instanceName O nome que a instancia tera
     * @param supervisorName O nome do supervisor do fluxo
     * @param objectsToPackage Os Ids a serem adicionados aos packages
     * @param objectsToAtachments Os anexos a serem inseridos no workflow
     * @param mapProcessVariable As variaveis de processo e os valores
     * 
     * @return O Id do Workflow
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfId startWorkflow(IDfProcess process, String instanceName, String supervisorName, List<IDfId> objectsToPackage, List<IDfId> objectsToAtachments, Map<String, Object> mapProcessVariable) throws Exception
    {
        process.fetch(null);

        IDfId workflowId = null;
        IDfWorkflowBuilder wfBldrObj = null;
        IDfWorkflow workflow = null;

        try
        {
            wfBldrObj = process.getSession().newWorkflowBuilder(process.getObjectId());
            workflow = wfBldrObj.getWorkflow();

            if (!com.documentum.fc.impl.util.StringUtil.isEmptyOrNull(instanceName))
            {
                workflow.setObjectName(instanceName);
            }
            if (!com.documentum.fc.impl.util.StringUtil.isEmptyOrNull(supervisorName))
            {
                workflow.setSupervisorName(supervisorName);
            }

            wfBldrObj.initWorkflow();
            workflowId = wfBldrObj.runWorkflow();

            addObjectsToPackage(objectsToPackage, getNameFirstActivity(process), process, wfBldrObj);
            addObjectsToAttachements(wfBldrObj, objectsToAtachments);
            setMapProcessVariable(mapProcessVariable, workflowId, process);
        }
        catch (Exception e)
        {
            if (workflowId != null)
            {
                IDfWorkflow wf = (IDfWorkflow) process.getSession().getObject(workflowId);

                wf.fetch(null);
                wf.abort();
                wf.destroy();
            }

            throw e;
        }

        return (workflowId);
    }

    /**
     * Metodo que adiciona as variaveis de processo ao workflow
     * 
     * @param mapProcessVariable As variaveis de processo
     * @param workflowId O id do Workflow
     * 
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    @SuppressWarnings("unchecked")
    private static void setMapProcessVariable(Map<String, Object> mapProcessVariable, IDfId workflowId, IDfProcess process) throws Exception
    {
        if (mapProcessVariable != null)
        {
            for (Entry<String, Object> entry : mapProcessVariable.entrySet())
            {
                IDfPersistentObject variable = (IDfSysObject) process.getSession().getObjectByQualification(Dqls.getProcessVariable(workflowId.getId(), entry.getKey()));

                if (variable != null)
                {
                    if (entry.getValue() instanceof String)
                    {
                        variable.setString("string_value", (String) entry.getValue());
                    }
                    else if (entry.getValue() instanceof Boolean)
                    {
                        variable.setBoolean("boolean_value", (Boolean) entry.getValue());
                    }
                    else if (entry.getValue() instanceof Integer)
                    {
                        variable.setInt("integer_value", (Integer) entry.getValue());
                    }
                    else if (entry.getValue() instanceof Double)
                    {
                        variable.setDouble("double_value", (Double) entry.getValue());
                    }
                    else if (entry.getValue() instanceof Date)
                    {
                        variable.setTime("time_value", new DfTime((Date) entry.getValue()));
                    }
                    else if (entry.getValue() instanceof Map<?, ?>)
                    {
                        Map<String, Object> mapSdt = (Map<String, Object>) entry.getValue();

                        if (mapSdt != null && !mapSdt.isEmpty())
                        {
                            IDfSysObject sdt = (IDfSysObject) process.getSession().getObject(variable.getId("sd_type_info_id"));

                            for (Entry<String, Object> entrySdt : mapSdt.entrySet())
                            {
                                String attrName = sdt.getRepeatingString("attr_element_type_name", sdt.findString("attr_sdt_name", entrySdt.getKey()));

                                if (entrySdt.getValue() instanceof String)
                                {
                                    variable.setString(attrName, (String) entrySdt.getValue());
                                }
                                else if (entrySdt.getValue() instanceof Boolean)
                                {
                                    variable.setBoolean(attrName, (Boolean) entrySdt.getValue());
                                }
                                else if (entrySdt.getValue() instanceof Integer)
                                {
                                    variable.setInt(attrName, (Integer) entrySdt.getValue());
                                }
                                else if (entrySdt.getValue() instanceof Double)
                                {
                                    variable.setDouble(attrName, (Double) entrySdt.getValue());
                                }
                                else if (entrySdt.getValue() instanceof Date)
                                {
                                    variable.setTime(attrName, new DfTime((Date) entrySdt.getValue()));
                                }
                            }
                        }
                    }

                    variable.save();
                }
                else
                {
                    throw new Exception("A variável '" + entry.getKey() + "' não existe no processo '" + workflowId.toString() + "'.");
                }
            }
        }
    }

    /**
     * Metodo que adiciona os Packages ao fluxo
     * 
     * @param objectsToPackage A lista de pacotes a serem adicionados
     * @param firstActivity O nome da primeira atividade
     * @param process O IDfProcess do template
     * @param wfBldrObj O Construtor do workflow
     * 
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    private static void addObjectsToPackage(List<IDfId> objectsToPackage, String firstActivity, IDfProcess process, IDfWorkflowBuilder wfBldrObj) throws Exception
    {
        IDfActivity firtActivity = (IDfActivity) process.getSession().getObject(process.getActivityIdByName(firstActivity));

        if (objectsToPackage != null)
        {
            if (firtActivity.getValueCount("r_package_type") < IS_SUPPORT_PACKAGE)
            {
                throw new DfException("A atividade '" + firtActivity.getObjectName() + "' do processo '" + process.getObjectName() + "' não suporta nenhum pacote..");
            }

            for (int i = 0; i < objectsToPackage.size(); i++)
            {
                IDfSysObject obj = (IDfSysObject) process.getSession().getObject(objectsToPackage.get(i));

                int idx = getPackageToTypeForActivity(firtActivity, obj);
                
                if (idx < 0)
                {
                    String s = "Não existe um pacote para o tipo '" + obj.getTypeName() + "' na atividade '" + firtActivity.getObjectName() + "' do processo '" + process.getObjectName() + "'.";
                    s = s.concat("Os tipos suportados para o processo são " + getTypesForActivityPackages(firtActivity));

                    throw new DfException(s);
                }

                IDfList list = new DfList(new IDfId[] { objectsToPackage.get(i) });
                wfBldrObj.addPackage(firtActivity.getObjectName(), firtActivity.getPortName(0), firtActivity.getRepeatingString("r_package_name", idx), obj.getTypeName(), null, false, list);
            }
        }
    }

    /**
     * Metodo que adiciona os anexos ao workflow
     * 
     * @param wfBldrObj O construtor do workflow
     * @param listAttachments A lista de anexos ao workflow
     * 
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    private static void addObjectsToAttachements(IDfWorkflowBuilder wfBldrObj, List<IDfId> listAttachments) throws Exception
    {
        if (listAttachments != null && !listAttachments.isEmpty())
        {
            for (IDfId dfId : listAttachments)
            {
                wfBldrObj.getWorkflow().addAttachment(((IDfSysObject) wfBldrObj.getProcess().getSession().getObject(dfId)).getTypeName(), dfId);
            }
        }
    }

    /**
     * Método que retorna o tipo de pacoate para a atividade.
     * 
     * @param act A atividade
     * @param obj O Objeto no pacote
     * 
     * @return O tipo do pacote
     * @throws Exception Caso algum erro ocorra, uma exceção será lançada.
     */
    public static int getPackageToTypeForActivity(IDfActivity act, IDfSysObject obj) throws Exception
    {
        int idx = -1;

        for (int i = 0; i < act.getValueCount("r_package_type"); i++)
        {
            String packageTypeName = act.getRepeatingString("r_package_type", i);

            if (obj.getTypeName().equals(packageTypeName) || obj.getType().isSubTypeOf(packageTypeName))
            {
                idx = i;
                break;
            }
            else
            {
                continue;
            }
        }
        return (idx);
    }

    /**
     * Método que retorna a lista de tipos aceitos para uma atividade
     * 
     * @param act A atividade
     * 
     * @return Os tipos aceitos
     * @throws Exception Caso algum erro ocorra, uma exceção será lançada.
     */
    public static List<String> getTypesForActivityPackages(IDfActivity act) throws Exception
    {
        List<String> r = new ArrayList<String>();

        for (int i = 0; i < act.getValueCount("r_package_type"); i++)
        {
            r.add(act.getRepeatingString("r_package_type", i));
        }
        return (r);
    }

    /**
     * Metodo que retorna o workitem atual do workflow
     * 
     * @param session A sessão
     * @param workflowId O Id do workflow
     * 
     * @return O IDfWorkitem atual
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfWorkitem getCurrentWorkitemByQuery(UidfSession session, IDfId workflowId) throws Exception
    {
        return ((IDfWorkitem) session.getIdfPersistentObject(UIdfCollections.getAttributeValue(new UIdfQuery(session).execQuery(Dqls.getAtividadeAtual(workflowId.getId())), VarsDfc.ATTR_OBJECT_ID, true)));
    }

    /**
     * Método que recupera o {@link IDfWorkitem} por consulta DQL
     * 
     * @param session A sessão
     * @param workflowId O ID do workflow
     * 
     * @return O {@link IDfWorkitem} encontrado
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public static IDfWorkitem getCurrentWorkitemByDfc(UidfSession session, IDfId workflowId) throws Exception
    {
        IDfWorkflow w = (IDfWorkflow) session.getIdfPersistentObject(workflowId);

        w.fetch(null);

        for (int i = 0; i < w.getActivityCount(); i++)
        {
            if (w.getActState(i) == IDfWorkflow.DF_ACT_STATE_ACTIVE || w.getActState(i) == IDfWorkflow.DF_ACT_STATE_DORMANT)
            {
                return ((IDfWorkitem) session.getIdfPersistentObject(w.getActDefId(i)));
            }
        }

        return (null);
    }

    /**
     * Metodo que finaliza um workflow em processo
     * 
     * @param wokflowId O id do workflow que se deseja terminar
     * 
     * @return Se True, o Workflow foi finalizado com sucesso
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public boolean terminateWorkflow(IDfId wokflowId) throws Exception
    {
        IDfWorkflow workflow = (IDfWorkflow) getUidfSession().getIdfPersistentObject(wokflowId);

        if (canAbort((workflow)))
        {
            workflow.fetch(null);
            workflow.abort();
        }
        if (canDelete(workflow))
        {
            workflow.fetch(null);
            workflow.destroy();
        }

        return (true);
    }

    /**
     * Metodo que retorna todos os Workflows em execucao no momento para um determinado processo
     * 
     * @param workflowName O nome do processo
     * 
     * @return Uma colecao contendo os dados dos workflows em execucao
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public IDfCollection getAllWorkflowsRunning(String workflowName) throws Exception
    {
        return (new UIdfQuery(getUidfSession()).execQuery(Dqls.getAllRunningWorkflows(workflowName)));
    }

    /**
     * Metodo que retorna o nome e o id de todas as atividades do workflow
     * 
     * @param workflowId O r_object_id do workflow
     * 
     * @return Um Map contendo os nomes e os Ids das atividades
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public Map<String, IDfId> getActivityNamesAndIds(IDfId workflowId) throws Exception
    {
        Map<String, IDfId> retorno = new HashMap<String, IDfId>();

        IDfId pId = ((IDfWorkflow) getUidfSession().getIdfPersistentObject(workflowId)).getProcessId();
        IDfProcess p = (IDfProcess) getUidfSession().getIdfPersistentObject(pId);

        for (int i = 0; i < p.getActivityCount(); i++)
        {
            retorno.put(p.getActivityName(i), p.getActivityIdByName(p.getActivityName(i)));
        }

        return (retorno);
    }

    public static String getPrimitiveProcessVariable(IDfWorkflow workflow, String var) throws Exception
    {
        List<String> l = Arrays.asList(getPrimitiveProcessVariablesName(workflow));
        int index = 0;

        if ((index = l.indexOf(var)) >= 0)
        {
            return (l.get(index));
        }

        return (null);
    }

    /**
     * Metodo que retorna os nomes das variaveis de processo do Workflow
     * 
     * @param workflow O Workflow
     * 
     * @return Um vetor com os nomes das variaveis
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static String[] getPrimitiveProcessVariablesName(IDfWorkflow workflow) throws Exception
    {
        return (((IDfActivityEx) workflow.getSession().getObject(workflow.getActDefId(0))).getVisibleVariableNames());
    }

    /**
     * Metodo que retorna o nome das variaveis de processo do tipo STD do Workflow
     * 
     * @param workflow O Workflow
     * 
     * @return Os nomes das variaveis de processo STD
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String[] getVariablesNameSDT(IDfWorkflow workflow) throws Exception
    {
        String[] processVariables = getPrimitiveProcessVariablesName(workflow);
        ArrayList<String> retorno = new ArrayList<String>();

        for (String var : processVariables)
        {
            IDfProcessVariableMetaData processVariableMetaData = getProcessEx(workflow).getVariableMetaData(var);

            if (!processVariableMetaData.isPrimitiveType())
            {
                if (retorno.isEmpty())
                {
                    retorno.add("SDTName=".concat(var));
                }
                for (IDfStructuredDataTypeAttribute j : processVariableMetaData.getStructuredDataType().getAttributes())
                {
                    retorno.add(j.getName());
                }
            }
        }
        return (((String[]) retorno.toArray(new String[retorno.size()])));
    }

    /**
     * Metodo que retorna os nomes das variaveis de processo que estao dentro de uma SDT
     * 
     * @param workflow O workflow
     * @param variable O nome da variavel de processo do tipo SDT
     * 
     * @return Um vetor contendo os nomes das variaveis de processo dentro da SDT
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String[] getVariablesNameSDT(IDfWorkflow workflow, String variable) throws Exception
    {
        IDfProcessVariableMetaData localIDfProcessVariableMetaData = getProcessEx(workflow).getVariableMetaData(variable);

        ArrayList<String> retorno = new ArrayList<String>();

        if (!localIDfProcessVariableMetaData.isPrimitiveType())
        {
            for (IDfStructuredDataTypeAttribute j : localIDfProcessVariableMetaData.getStructuredDataType().getAttributes())
            {
                retorno.add(j.getName());
            }
        }
        if (!retorno.isEmpty())
        {
            return (((String[]) retorno.toArray(new String[retorno.size()])));
        }
        throw new Exception("A Variavel [ " + variable + " ] nao e do tipo SDT");
    }

    /**
     * Metodo que retorna o valor da variavel de processo primitiva
     * 
     * @param workflow O workflow
     * @param variableName O nome da variavel de processo primitva
     * 
     * @return O valor da variavel de proceso
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public Object getValueVariablePrimitive(IDfWorkflow workflow, String variableName) throws Exception
    {
        return (((IDfWorkflowEx) workflow).getPrimitiveVariableValue(variableName));
    }

    /**
     * Metodo que retorna o valor da variavel dentro do SDT
     * 
     * @param workflow O workflow
     * @param nameSDT O nome da SDT
     * @param variableName O nome da variavel de processo
     * 
     * @return O valor da variavel
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public Object getValueVariableSDT(IDfWorkflow workflow, String nameSDT, String variableName) throws Exception
    {
        return (((IDfWorkflowEx) workflow).getStructuredDataTypeAttrValue(nameSDT, variableName));
    }

    /**
     * Metodo que seta um valor na variavel de processo primitiva
     * 
     * @param workflow O workflow
     * @param variable O nome da variavel de processo primitiva
     * @param value O Valor a ser colocado na variavel
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void setValuePrimitiveVariable(IDfWorkitem workitem, String variable, Object value) throws Exception
    {
        IDfWorkitemEx localWorkitemEx = (IDfWorkitemEx) workitem;
        localWorkitemEx.setPrimitiveObjectValue(variable, value);
    }

    /**
     * Metodo que seta um valor na variavel de processo SDT
     * 
     * @param workitem O workflow
     * @param nameSDT O nome da variavel SDT
     * @param variableName O nome da variavel dentro da SDT
     * @param value O valor que se deseja setar
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void setValueSDTVariable(IDfWorkitem workitem, String nameSDT, String variableName, Object value) throws Exception
    {
        IDfWorkitemEx localWorkitemEx = (IDfWorkitemEx) workitem;
        localWorkitemEx.setStructuredDataTypeAttrValue(nameSDT, variableName, value);
    }

    /**
     * Metodo que seta um valor na variavel de processo SDT
     * 
     * @param workitem O workflow
     * @param nameSDT O nome da variavel SDT
     * @param variableName O nome da variavel dentro da SDT
     * @param value Os valores que se deseja setar
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void setValueSDTVariable(IDfWorkitem workitem, String nameSDT, String variableName, Object value[]) throws Exception
    {
        IDfWorkitemEx localWorkitemEx = (IDfWorkitemEx) workitem;
        localWorkitemEx.setStructuredDataTypeAttrValue(nameSDT, variableName, value);
    }

    /**
     * Metodo que testa se uma variavel de processo e primitiva ou nao
     * 
     * @param workflow O workflow
     * @param nomeVariavel O nome da variavel
     * 
     * @return <blockquote>true, e primitiva</blockquote> <blockquote>false, nao e primitiva</blockquote>
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean isVariablePrimitiva(IDfWorkflow workflow, String nomeVariavel) throws Exception
    {
        return (getProcessEx(workflow).getVariableMetaData(nomeVariavel).isPrimitiveType());
    }

    /**
     * Metodo que retorna um processo apartir de uma workflow
     * 
     * @param workflow O workflow
     * 
     * @return O processo
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfProcessEx getProcessEx(IDfWorkflow workflow) throws Exception
    {
        return (IDfProcessEx) getUidfSession().getIdfPersistentObject(workflow.getProcessId());
    }

    /**
     * Metodo que retorna o Package de um workflow
     * 
     * @param wi O Workitem atual
     * 
     * @return O IDfPackage do workflow
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfPackage getPackage(IDfWorkitem wi) throws Exception
    {
        IDfPackage dfPackage = null;

        if (wi != null)
        {
            IDfCollection idfCollection = wi.getPackages(null);

            while (idfCollection.next())
            {
                dfPackage = (IDfPackage) getUidfSession().getIdfPersistentObject(idfCollection.getString(VarsDfc.ATTR_OBJECT_ID));
                if (UIdfCollections.canCloseCollection(idfCollection))
                {
                    idfCollection.close();
                }
                return (dfPackage);
            }
        }
        return (null);
    }

    /**
     * Metodo que recupera os Notes adicionados ao processo
     * 
     * @param workItem O Workitem atual
     * 
     * @return Uma lista com os notes do processo
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public List<NoteData> getNotes(IDfWorkitem workItem) throws Exception
    {
        List<NoteData> lista = new ArrayList<NoteData>();
        IDfCollection col = workItem.getPackages(null);

        while (col != null && col.next())
        {
            IDfPackage pack = (IDfPackage) getUidfSession().getIdfPersistentObject(col.getString("r_object_id"));

            for (int i = 0; i < pack.getNoteCount(); i++)
            {
                NoteData noteData = new NoteData();
                noteData.setNoteId(pack.getNoteId(i));
                noteData.setNoteCreationDate(pack.getNoteCreationDate(i));
                noteData.setNoteFlag(pack.getNoteFlag(i));
                noteData.setNoteText(pack.getNoteText(i));
                noteData.setNoteWriter(pack.getNoteWriter(i));
                lista.add(noteData);
            }
        }
        return lista;
    }

    /**
     * Metodo que retorna os anexos de um workflow
     * 
     * @param witem O Workitem atual
     * 
     * @return Os anexos do workflow
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public List<IDfId> getWfAttachments(IDfWorkitem witem) throws Exception
    {
        List<IDfId> wfAttachmentIds = new ArrayList<IDfId>();
        IDfCollection coll = witem.getAttachments();

        while (coll.next())
        {
            wfAttachmentIds.add(coll.getId(VarsDfc.ATTR_COMPONENT_ID));
        }

        return wfAttachmentIds;
    }

    /**
     * Metodo que testa se um workflow pode ser abortado
     * 
     * @param w O workflow
     * 
     * @return <blockquote>true, pode ser abortado</blockquote> <blockquote>false, nao pode ser abortado</blockquote>
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static boolean canAbort(IDfWorkflow w) throws Exception
    {
        return (w.getRuntimeState() == VarsDfc.WORKFLOW_STATE_RUNNING) || (w.getRuntimeState() == VarsDfc.WORKFLOW_STATE_HALTED);
    }

    /**
     * Metodo que testa se um workflow pode ser deletado
     * 
     * @param w O workflow
     * 
     * @return <blockquote>true, pode ser deletado</blockquote> <blockquote>false, nao pode ser deletado</blockquote>
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static boolean canDelete(IDfWorkflow w) throws Exception
    {
        return (w.getRuntimeState() == VarsDfc.WORKFLOW_STATE_DORMANT) || (w.getRuntimeState() == VarsDfc.WORKFLOW_STATE_TERMINATED);
    }

    /**
     * Metodo que retorna um Workflow
     * 
     * @param WfId O Id do Workflow
     * 
     * @return O Objeto do tipo IDfWorkflow
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfWorkflow getWorkflow(IDfId WfId) throws Exception
    {
        return ((IDfWorkflow) getUidfSession().getIdfPersistentObject(WfId));
    }

    /**
     * Metodo que retorna o nome da atividade do Workitem
     * 
     * @param wi O Workitem
     * 
     * @return O nome da atividade
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static String getActivityName(IDfWorkitem wi) throws Exception
    {
        return (wi.getActivity().getObjectName());
    }

    /**
     * Metodo que retorna o objeto de atividade do Workitem atual
     * 
     * @param wi O Workitem
     * 
     * @return O Objeto da atividade
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static IDfActivity getActivity(IDfWorkitem wi) throws Exception
    {
        return (wi.getActivity());
    }

    /**
     * Metodo que aprova uma tarefa do Workitem
     * 
     * @param wi O Workitem
     * 
     * @return True, O Workitem foi aprovado<br>
     *         False, O Workitem nao pode ser aprovado
     * 
     * @exception Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean aproveTask(IDfWorkitem wi) throws Exception
    {
        if (wi.getRuntimeState() == IDfWorkitem.DF_WI_STATE_PAUSED)
        {
            wi.resume();
        }

        if (wi.getRuntimeState() == IDfWorkitem.DF_WI_STATE_ACQUIRED)
        {
            if (wi.getPerformerName().equals(getUidfSession().getCompleteUserName()))
            {
                wi.complete();
            }
            else
            {
                printErr("A Tarefa ja esta atribuida ao usuario ".concat(wi.getPerformerName()));
                return (false);
            }
        }
        else
        {
            wi.acquire();
            wi.complete();
        }

        return (wi.getRuntimeState() == IDfWorkitem.DF_WI_STATE_FINISHED);
    }
}
