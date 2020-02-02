package jutildfc.utilsdfc;

import jutildfc.data.VarsDfc;
import jutildfc.utilsjava.ClassPathUtil;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfGroup;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.client.IDfUser;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.common.IDfValue;
import com.documentum.fc.impl.util.StringUtil;
import com.documentum.operations.IDfCheckinOperation;
import com.documentum.operations.IDfCheckoutOperation;
import com.documentum.operations.IDfCopyOperation;
import com.documentum.operations.IDfDeleteOperation;
import com.documentum.operations.IDfExportOperation;
import com.documentum.operations.IDfImportOperation;
import com.documentum.operations.IDfMoveOperation;

public class UidfSession
{
    private IDfSessionManager  sessionManager       = null;
    private DfClientX          clientx              = null;
    private IDfClient          client               = null;

    private IDfSession         session              = null;
    private IDfLoginInfo       loginInfo            = null;

    public static final String ATTRIB_SELECTED_ROLE = "selectedrole";
    public static final String ATTRIB_APP           = "app";
    public static String       CONNECTION_MAP       = "";

    /**
     * Construtor padrão
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UidfSession() throws Exception
    {
        initVars();
    }

    /**
     * Construtor parametrizado
     * 
     * @param configFolder O endereço da pasta que contem o arquivo dfc.propertie
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UidfSession(String configFolder) throws Exception
    {
        if (!StringUtil.isEmptyOrNull(configFolder))
        {
            if (!ClassPathUtil.isAddNoClassPath(configFolder))
            {
                ClassPathUtil.addFile(configFolder);
            }
        }

        initVars();
    }

    /**
     * Metodo que se conecta a Docbase configurada
     * 
     * @return Se True, Entao conectado com suscesso
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean connectToDocbaseWithCredentials(String userName, String password, String docbase) throws Exception
    {
        getLoginInfo().setUser(userName);
        getLoginInfo().setPassword(password);
        getSessionManager().setIdentity(docbase, loginInfo);

        setSession(getSessionManager().getSession(docbase));

        return (isConnected());
    }

    /**
     * Método que conecta a uma docbase sem a necessidade de um dfc.properties
     * 
     * @param userName
     * @param password
     * @param docbase
     * @param docbrokerHost
     * @param docbrokerPort
     * @return
     * @throws Exception
     */
    public boolean connectToDocbaseWithoutProperties(String userName, String password, String docbase, String docbrokerHost, String docbrokerPort) throws Exception
    {
        initVars();

        IDfTypedObject config = client.getClientConfig();

        config.setString("primary_host", docbrokerHost);
        config.setInt("primary_port", Integer.parseInt(docbrokerPort));

        setSessionManager(getClient().newSessionManager());

        getLoginInfo().setUser(userName);
        getLoginInfo().setPassword(password);
        getSessionManager().setIdentity(docbase, loginInfo);

        setSession(getSessionManager().getSession(docbase));

        return (isConnected());
    }

    /**
     * Metodo que desconecta a sesao atual da docBase
     * 
     * @return Se True, Conectado com suscesso
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean disconnectFromDocbase() throws Exception
    {
        if (getSession() != null)
        {
            String docbase = getSession().getDocbaseName();

            getSessionManager().release(getSession());
            getSessionManager().clearIdentity(docbase);
            setSession(null);

            return (getSession() == null);
        }
        return false;
    }

    /**
     * Metodo que verifica se a sessao atual esta conectada a docBase
     * 
     * @return Se True, Entao Conectado
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean isConnected() throws Exception
    {
        return (getSession().isConnected());
    }

    /**
     * Método que retorna um IDfSysObject
     * 
     * @param object O Path ou Id do objeto
     * 
     * @return O IDfSysObject encontrado
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfSysObject getIdfSysObject(Object object) throws Exception
    {
        if (object instanceof IDfValue)
        {
            return (getIdfSysObject(((IDfValue) object).asString()));
        }
        else if (object instanceof IDfId)
        {
            return (getIdfSysObject(((IDfId) object).getId()));
        }
        else if (object instanceof String)
        {
            return (getIdfSysObject(object.toString()));
        }
        return (null);
    }

    /**
     * Método que retorna um IDfPersistentObject
     * 
     * @param object O Path ou Id do objeto
     * 
     * @return O IDfPersistentObject encontrado
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfPersistentObject getIdfPersistentObject(Object object) throws Exception
    {
        if (object instanceof IDfValue)
        {
            return (getIdfPersistentObject(((IDfValue) object).asString()));
        }
        else if (object instanceof IDfId)
        {
            return (getIdfPersistentObject(((IDfId) object).getId()));
        }
        else if (object instanceof String)
        {
            return (getIdfPersistentObject(object.toString()));
        }
        return (null);
    }

    /**
     * Metodo que retorna um IDfSysObject do repositorio
     * 
     * @param value O Object Id ou Path do Objeto no Repositorio
     * 
     * @return O IDfSysObject encontrado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    private IDfSysObject getIdfSysObject(String value) throws Exception
    {
        return ((IDfSysObject) getIdfPersistentObject(value));
    }

    /**
     * Metodo que retorna um IDfPersistentObject do repositorio
     * 
     * @param value O Object Id ou Path do Objeto no Repositorio
     * 
     * @return O IDfPersistentObject encontrado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    private IDfPersistentObject getIdfPersistentObject(String value) throws Exception
    {
        if (DfId.isObjectId(value))
        {
            return (getSession().getObject(new DfId(value)));
        }
        else
        {
            return (getSession().getObjectByPath(value));
        }
    }

    /**
     * Método que retorna o grupo com o nome passado
     * 
     * @param groupName O nome do grupo que se deseja recuperar
     * 
     * @return O object {@link IDfGroup}
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfGroup getIdfGroup(String groupName) throws Exception
    {
        return (getSession().getGroup(groupName));
    }

    /**
     * Método que retorna um usuário da docbase
     * 
     * @param userName O nome do usuário desejado
     * 
     * @return O objecto {@link IDfUser}
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfUser getIdfUser(String userName) throws Exception
    {
        return (getSession().getUser(userName));
    }

    /**
     * Metodo que retorna um IDfPersistentObject
     * 
     * @param dql A Dql para recuperar o IDfPersistentObject
     * @return O IDfPersistentObject recuperado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfPersistentObject getObjectByQualification(String dql) throws Exception
    {
        return (getSession().getObjectByQualification(dql));
    }

    /**
     * Metodo que cria um novo IDfSysObject Object
     * 
     * @param typeObject O tipo do documento, a classe {@link VarsDfc} possui alguns nomes de tipos ja configurados
     * 
     * @return O Objeto IDfSysObject
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfSysObject createNewObject(String typeObject) throws Exception
    {
        return ((IDfSysObject) getSession().newObject(typeObject));
    }

    public IDfCollection getInstalledProcess(String additionalsAttr) throws Exception
    {
        return (getSession().getRunnableProcesses(additionalsAttr));
    }

    /**
     * Metodo que retorna o nome completo do usuario logado na sessao
     * 
     * @return O nome completo do usuario logado
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String getCompleteUserName() throws Exception
    {
        return (getSession().getLoginUserName());
    }

    /**
     * Metodo que retorna o Ticket de login do usuario logado
     * 
     * @return O Ticket do usuario logado
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String getUserLoginTicket() throws Exception
    {
        return (getSession().getLoginTicket());
    }

    /**
     * Método que recupera as tarefas da Inbox de um determinado usuários
     * 
     * @param userName O valor da propriedade user_name do usuário
     * 
     * @return O {@link IDfCollection} contendo as tarefas do usuário
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfCollection getTasksForUser(String userName, String additionalsAttr) throws Exception
    {
        return (getSession().getTasks(userName, IDfSession.DF_TASKS_AND_NOTIFICATIONS, additionalsAttr, "task_name"));
    }

    /**
     * Metodo que imprime as informacoes da sessao atual
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    @SuppressWarnings("deprecation")
    public void displaySessionInfo() throws Exception
    {
        System.out.println("Docbase      : ".concat(getSession().getDocbaseName()));
        System.out.println("Server Vers  : ".concat(getSession().getServerVersion()));
        System.out.println("DBMS         : ".concat(getSession().getDBMSName()));
        System.out.println("Owner        : ".concat(getSession().getDocbaseOwnerName()));
        System.out.println("Sess Id      : ".concat(getSession().getSessionId()));
        System.out.println("DMCL Sess Id : ".concat(getSession().getDMCLSessionId()));
        System.out.println("Docbase Id   : ".concat(getSession().getDocbaseId()));
        System.out.println("Scope        : ".concat(getSession().getDocbaseScope()));
        System.out.println("User         : ".concat(getSession().getLoginUserName()));
        System.out.println("Login Ticket : ".concat(getSession().getLoginTicket()));
        System.out.println("Security     : ".concat(getSession().getSecurityMode()));
        System.out.println("---------------------------------------------");
    }

    /**
     * Metodo que retorna o IDfCheckinOperation do ClientX()
     * 
     * @return O IDfCheckinOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfCheckinOperation getCheckinOperation() throws Exception
    {
        return (getClientx().getCheckinOperation());
    }

    /**
     * Metodo que retorna o IDfCheckoutOperation do ClientX()
     * 
     * @return O IDfCheckoutOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfCheckoutOperation getCheckoutOperation() throws Exception
    {
        return (getClientx().getCheckoutOperation());
    }

    /**
     * Metodo que retorna o IDfCopyOperation do ClientX()
     * 
     * @return O IDfCopyOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfCopyOperation getCopyOperation() throws Exception
    {
        return (getClientx().getCopyOperation());
    }

    /**
     * Metodo que retorna o IDfDeleteOperation do ClientX()
     * 
     * @return O IDfDeleteOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfDeleteOperation getDeleteOperation() throws Exception
    {
        return (getClientx().getDeleteOperation());
    }

    /**
     * Metodo que retorna o IDfMoveOperation do ClientX()
     * 
     * @return O IDfMoveOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfMoveOperation getMoveOperation() throws Exception
    {
        return (getClientx().getMoveOperation());
    }

    /**
     * Metodo que retorna o IDfImportOperation do ClientX()
     * 
     * @return O IDfImportOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfImportOperation getImportOperation() throws Exception
    {
        return (getClientx().getImportOperation());
    }

    /**
     * Metodo que retorna o IDfExportOperation do ClientX()
     * 
     * @return O IDfExportOperation
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfExportOperation getExportOperation() throws Exception
    {
        return (getClientx().getExportOperation());
    }

    /**
     * Método que inicia as variáveis usada pela classe
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    private void initVars() throws Exception
    {
        clientx = new DfClientX();
        client = clientx.getLocalClient();
        sessionManager = client.newSessionManager();
        loginInfo = clientx.getLoginInfo();
    }

    /**
     * Metodo que retorna o Valor da variavel sessionManager
     *
     * @return O valor da variavel sessionManager
     */
    public IDfSessionManager getSessionManager()
    {
        return sessionManager;
    }

    /**
     * Metodo que seta o Valor da variavel sessionManager
     *
     * @param sessionManager O valor da variavel sessionManager
     */
    public void setSessionManager(IDfSessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    /**
     * Metodo que retorna o Valor da variavel clientx
     *
     * @return O valor da variavel clientx
     */
    public DfClientX getClientx()
    {
        return clientx;
    }

    /**
     * Metodo que seta o Valor da variavel clientx
     *
     * @param clientx O valor da variavel clientx
     */
    public void setClientx(DfClientX clientx)
    {
        this.clientx = clientx;
    }

    /**
     * Metodo que retorna o Valor da variavel client
     *
     * @return O valor da variavel client
     */
    public IDfClient getClient()
    {
        return client;
    }

    /**
     * Metodo que seta o Valor da variavel client
     *
     * @param client O valor da variavel client
     */
    public void setClient(IDfClient client)
    {
        this.client = client;
    }

    /**
     * Metodo que retorna o Valor da variavel session
     *
     * @return O valor da variavel session
     */
    public IDfSession getSession()
    {
        return session;
    }

    /**
     * Metodo que seta o Valor da variavel session
     *
     * @param session O valor da variavel session
     */
    public void setSession(IDfSession session)
    {
        this.session = session;
    }

    /**
     * Metodo que retorna o Valor da variavel loginInfo
     *
     * @return O valor da variavel loginInfo
     */
    public IDfLoginInfo getLoginInfo()
    {
        return loginInfo;
    }

    /**
     * Metodo que seta o Valor da variavel loginInfo
     *
     * @param loginInfo O valor da variavel loginInfo
     */
    public void setLoginInfo(IDfLoginInfo loginInfo)
    {
        this.loginInfo = loginInfo;
    }
}
