package jutildfc.interfaces;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jutildfc.utilsdfc.UIdfLogger;

import com.documentum.fc.client.DfSingleDocbaseModule;
import com.documentum.fc.client.IDfModule;
import com.documentum.fc.methodserver.DfMethodArgumentManager;
import com.documentum.fc.methodserver.DfStandardJobArguments;
import com.documentum.fc.methodserver.IDfMethod;

@SuppressWarnings({ "rawtypes" })
public abstract class UIdfMethod extends DfSingleDocbaseModule implements IDfMethod, IDfModule
{
    public static final int METHOD_EXEC_SUSSCEFULL = 0;
    public static final int METHOD_EXEC_FAILED     = 1;

    private UIdfLogger logger;
    private PrintWriter print;

    private Map parametros;
    private Map<Integer, String> niveis = new HashMap<Integer, String>();

    private DfStandardJobArguments jobArguments;
    private DfMethodArgumentManager manageArguments;

    
    protected static final int LEVEL_DEBUG = 0;
    protected static final int LEVEL_INFO  = 1;
    protected static final int LEVEL_WARN  = 2;
    protected static final int LEVEL_ERROR = 3;
    
    @Override
    public int execute(Map mapParameters, PrintWriter print) throws Exception
    {
	setPrint(print);
	setLogger(new UIdfLogger(getPrint()));
	setParametros(mapParameters);
	setManageArguments(new DfMethodArgumentManager(mapParameters));
	setJobArguments(new DfStandardJobArguments(getManageArguments()));
	
	populateNiveis();
	
	try
	{
	    if(getSession().isConnected())
	    {
		getSession().disconnect();
		printErroToJobReport("Conectado a docbase", null);
	    }
	    else
	    {
		printErroToJobReport("Não conectado a docbase", null);
	    }
	    
	    return executeMethod();
	} 
	catch (Exception e)
	{
	    if(getPrint() != null)
	    {
		printErroToJobReport("Erro ao iniciar Job", e);
	    }
	    
	    return METHOD_EXEC_FAILED;
	}
    }
    
    public abstract int executeMethod() throws Exception;

    public abstract int executeTestMethod(Object... obj) throws Exception;

    public String getStringArgument(String paramName) throws Exception
    {
	return getManageArguments().getString(paramName);
    }

    public int getInt(String paramName) throws Exception
    {
	return getManageArguments().getInt(paramName).intValue();
    }
    
    public void printInfoToJobReport(String message) throws Exception
    {
	getLogger().writeInfo(message);
    }

    public void printDebugToJobReport(String message) throws Exception
    {
	getLogger().writeDebug(message);
    }
    
    public void printErroToJobReport(String message, Exception e) throws Exception
    {
	getLogger().writeError(message, e);
    }
    
    public void printWarnToJobReport(String message, Exception e) throws Exception
    {
	getLogger().writeWarn(message, e);
    }
    
    private void populateNiveis()
    {
	niveis.put(LEVEL_DEBUG, "DEBUG");
	niveis.put(LEVEL_INFO, "INFO");
	niveis.put(LEVEL_WARN, "WARN");
	niveis.put(LEVEL_ERROR, "ERROR");
    }
    
    public UIdfLogger getLogger()
    {
	return logger;
    }

    public void setLogger(UIdfLogger logger)
    {
	this.logger = logger;
    }

    public Map getParametros()
    {
	return parametros;
    }

    public void setParametros(Map parametros)
    {
	this.parametros = parametros;
    }

    public PrintWriter getPrint()
    {
        return print;
    }

    public void setPrint(PrintWriter print)
    {
        this.print = print;
    }

    public DfStandardJobArguments getJobArguments()
    {
        return jobArguments;
    }

    public void setJobArguments(DfStandardJobArguments jobArguments)
    {
        this.jobArguments = jobArguments;
    }

    public DfMethodArgumentManager getManageArguments()
    {
        return manageArguments;
    }

    public void setManageArguments(DfMethodArgumentManager manageArguments)
    {
        this.manageArguments = manageArguments;
    }
}
