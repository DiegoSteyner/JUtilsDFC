package jutildfc.utilsdfc;

import java.io.File;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfVirtualDocument;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.operations.IDfCheckinOperation;
import com.documentum.operations.IDfCheckoutOperation;
import com.documentum.operations.IDfCopyOperation;
import com.documentum.operations.IDfDeleteOperation;
import com.documentum.operations.IDfExportNode;
import com.documentum.operations.IDfExportOperation;
import com.documentum.operations.IDfImportNode;
import com.documentum.operations.IDfImportOperation;
import com.documentum.operations.IDfMoveOperation;
import com.documentum.operations.IDfOperation;
import com.documentum.operations.IDfOperationError;
import com.documentum.operations.IDfOperationMonitor;
import com.documentum.operations.IDfOperationNode;
import com.documentum.operations.IDfOperationStep;

import jutildfc.data.VarsDfc;

public class UIdfOperations
{
    private boolean     monitorDeOperacao = false;
    private UIdfFile    file              = new UIdfFile();
    private UidfSession session           = null;

    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto {@link UidfSession} configurado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UIdfOperations(UidfSession session)
    {
        this.session = session;
    }

    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto {@link UidfSession} configurado
     * 
     * @param monitorDeOperacao <blockquote>true, ativar monitor de operacoes</blockquote>
     * 							<blockquote>false, desativar monitor de operacoes</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UIdfOperations(UidfSession session, boolean monitorDeOperacao)
    {
        this.session = session;
        this.monitorDeOperacao = monitorDeOperacao;
    }

    /**
     * Metodo que executa um check-out no Objeto
     * 
     * @param objIdPath O Id do objeto que se deseja efetuar o Check-out ou o Path
     * @return	<blockquote>true, Check-out efetuado com suscesso</blockquote>
     * 		<blockquote>false, Check-out nao realizado</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean checkOutObject(String objIdPath) throws Exception
    {
        boolean retVal = false;

        IDfSysObject sysObj = getSession().getIdfSysObject(objIdPath);

        if (sysObj.isCheckedOut())
        {
            System.out.println("O Objeto [".concat(objIdPath).concat("] ja esta em checkout"));
        }
        else
        {
            sysObj.checkout();
            sysObj.getFile(sysObj.getObjectName());
            retVal = true;
        }

        return retVal;
    }

    /**
     * Metodo que executa um Check-in no Objeto
     * 
     * @param objIdPath O Id do objeto que se deseja realizar Check-in ou o Path
     * 
     * @return O novo Id do Objeto
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String checkInObject(String objIdPath) throws Exception
    {
        IDfSysObject sysObj = getSession().getIdfSysObject(objIdPath);

        if (sysObj.isCheckedOut())
        {
            System.out.println("Object version: ".concat("CURRENT, ").concat(sysObj.getVersionPolicy().getNextMinorLabel()));

            sysObj.setFile(sysObj.getObjectName());
            String newSysObjId = sysObj.checkin(false, "").getId();
            file.removeLocalFile(sysObj);

            return (newSysObjId);
        }
        else
        {
            System.out.println("O Objeto [".concat(objIdPath).concat("] nao esta em checkout"));
            return (null);
        }
    }

    /**
     * Metodo que executa um Check-out via CheckOutOperation
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * 
     * @return	<blockquote>true, Check-Out efetuado com suscesso</blockquote>
     * 		<blockquote>false, Check-Out falhou</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean checkOutOperation(String objIdPath) throws Exception
    {

        IDfCheckoutOperation check = getSession().getCheckoutOperation();
        IDfSysObject obj = getSession().getIdfSysObject(objIdPath);

        if (!obj.isCheckedOut())
        {
            if (obj.isVirtualDocument())
            {
                check.add(((IDfVirtualDocument) obj.asVirtualDocument("", false)));
            }
            else
            {
                check.add(obj);
            }

            if (isMonitorDeOperacao())
            {
                check.setOperationMonitor(new monitorDeOperacoes());
            }
            return (check.execute());
        }
        else
        {
            System.out.println("Esta em checkout");
        }
        return (false);
    }

    /**
     * Metodo que executa um Check-out via CheckOutOperation
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param directory O diretorio onde se deseja que seja feito 
     * o Check-Out do objeto
     * 
     * @return	<blockquote>true, Check-Out efetuado com suscesso</blockquote>
     * 			<blockquote>false, Check-Out falhou</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean checkOutOperation(String objIdPath, String directory) throws Exception
    {

        IDfCheckoutOperation check = getSession().getCheckoutOperation();
        IDfSysObject obj = getSession().getIdfSysObject(objIdPath);

        if (!obj.isCheckedOut())
        {
            if (obj.isVirtualDocument())
            {
                check.add(((IDfVirtualDocument) obj.asVirtualDocument("", false)));
            }
            else
            {
                check.add(obj);
                check.setDestinationDirectory(directory);
            }

            if (isMonitorDeOperacao())
            {
                check.setOperationMonitor(new monitorDeOperacoes());
            }

            return (check.execute());

        }
        else
        {
            System.out.println("Esta em checkout");
        }
        return (false);
    }

    /**
     * Metodo que faz um Check-in via check-in Operation
     * 
     * @param objIdPath O r_object_id do objeto ou o path
     * @param typeCheckin O tipo de checkin {@link VarsDfc}
     * 
     * @param keepLocalCopy	<blockquote>true, A copia local sera mantida</blockquote>
     * 						<blockquote>false, A copia local sera deletada</blockquote>
     * 
     * @param symbolicLabel O Symbolic Label do objeto
     * 
     * @param setCurrent <blockquote>true, Sera aplicado o Symbolic Label CURRENT</blockquote>
     * 					 <blockquote>false, Nao sera aplicado o Symbolic Label CURRENT</blockquote>
     * 
     * @return O Id do Objeto dado em Check-in Recuperado via pesquisa em dm_sysobject
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String checkInOperation(String objIdPath, int typeCheckin, boolean keepLocalCopy, String symbolicLabel, boolean setCurrent) throws Exception
    {
        IDfSysObject objeto = getSession().getIdfSysObject(objIdPath);

        if (objeto.isCheckedOut())
        {
            IDfCheckinOperation check = getSession().getClientx().getCheckinOperation();

            if (objeto.isVirtualDocument())
            {
                check.add(((IDfVirtualDocument) objeto.asVirtualDocument("", false)));
            }
            else
            {
                check.add(objeto);
            }

            check.setCheckinVersion(typeCheckin);
            check.setKeepLocalFile(keepLocalCopy);

            if (setCurrent)
            {
                check.setVersionLabels(VarsDfc.LABEL_CURRENT.concat(",").concat(symbolicLabel));
            }
            else
            {
                check.setVersionLabels(symbolicLabel);
            }
            if (isMonitorDeOperacao())
            {
                check.setOperationMonitor(new monitorDeOperacoes());
            }

            if (check.execute())
            {
                return (getSession().getObjectByQualification("dm_sysobject where i_chronicle_id = '" + objeto.getChronicleId() + "'").getObjectId().getId());
            }
            else
            {
                return ("checkinFalhou");
            }
        }

        return ("EmCheckin");
    }

    /**
     * Metodo que cancela um Check-out em um Objeto
     * 
     * @param objId O id do Objeto em Check-out
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void cancelCheckout(String objId) throws Exception
    {
        IDfSysObject sysObj = getSession().getIdfSysObject(objId);
        if (sysObj.isCheckedOut())
        {
            sysObj.cancelCheckout();
            file.removeLocalFile(sysObj);
        }
        else
        {
            System.out.println("O Objeto [".concat(objId).concat("] nao0 esta em checkout"));
        }
    }

    /**
     * Metodo que copia um Objeto para uma Pasta especifica
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * 
     * @param folderId O id da pasta para onde se deseja copiar o arquivo
     * 
     * @return	<blockquote>true, Arquivo copiado com suscesso</blockquote>
     * 			<blockquote>false, O arquivo nao pode ser copiado</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean copyOperation(String objIdPath, String folderId) throws Exception
    {
        IDfCopyOperation copyOp = getSession().getCopyOperation();
        IDfSysObject objeto = getSession().getIdfSysObject(objIdPath);

        copyOp.setDestinationFolderId(new DfId(folderId));

        if (objeto.isVirtualDocument())
        {
            copyOp.add(((IDfVirtualDocument) objeto.asVirtualDocument("", false)));
        }
        else
        {
            copyOp.add(objeto);
        }

        if (isMonitorDeOperacao())
        {
            copyOp.setOperationMonitor(new monitorDeOperacoes());
        }
        return (copyOp.execute());
    }

    /**
     * Metodo que deleta um arquivo do repositorio
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * 
     * @param tipoDelete O tipo de delete a ser Efetuado {@link VarsDfc}
     * 				
     * @return	<blockquote>true, Arquivo deletado com suscesso</blockquote>
     * 			<blockquote>false, O arquivo nao pode ser deletado</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro sera lancado uma excessao
     */
    public boolean deleteOperation(String objIdPath, int tipoDelete) throws Exception
    {
        IDfDeleteOperation del = getSession().getDeleteOperation();
        IDfSysObject obj = getSession().getIdfSysObject(objIdPath);

        if (!obj.isCheckedOut())
        {
            if (obj.isVirtualDocument())
            {
                del.add(((IDfVirtualDocument) obj.asVirtualDocument("", false)));
            }
            else
            {
                del.add(obj);
            }
            del.setVersionDeletionPolicy(tipoDelete);

            if (isMonitorDeOperacao())
            {
                del.setOperationMonitor(new monitorDeOperacoes());
            }
            return (del.execute());
        }
        else
        {
            System.out.println("Objeto[ " + objIdPath + " ] esta em Checkout");
            return (false);
        }
    }

    /**
     * Metodo que move um arquivo de um lugar para outro
     * 
     * @param objIdPath O r_object_id ou Path do objeto
     * 
     * @param folderDestiny O id da Pasta para onde se deseja mover o arquivo
     * 
     * @return	<blockquote>true, Arquivo movido com suscesso</blockquote>
     * 		<blockquote>false, O arquivo nao pode ser movido</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro sera lancado uma excessao
     */
    public boolean moveOperation(String objIdPath, String folderDestiny) throws Exception
    {
        IDfMoveOperation mover = getSession().getMoveOperation();
        IDfSysObject obj = getSession().getIdfSysObject(objIdPath);

        if (!obj.isCheckedOut())
        {
            if (obj.isVirtualDocument())
            {
                mover.add(((IDfVirtualDocument) obj.asVirtualDocument("", false)));
            }
            else
            {
                mover.add(obj);
            }

            mover.setSourceFolderId(obj.getFolderId(0));
            mover.setDestinationFolderId(new DfId(folderDestiny));

            if (isMonitorDeOperacao())
            {
                mover.setOperationMonitor(new monitorDeOperacoes());
            }
            return (mover.execute());
        }
        else
        {
            System.out.println("O Objeto [ " + objIdPath + " ] Esta em Check-Out");
            return (false);
        }
    }

    /**
     * Metodo que move um arquivo de um lugar para outro
     * 
     * @param objIdPath O r_object_id ou Path do objeto
     * @param folderDestinyId O id da Pasta para onde se deseja mover o arquivo
     * @param sourceFolderId O id da Pasta para onde o documento esta
     * 
     * @return	<blockquote>true, Arquivo movido com suscesso</blockquote>
     * 		<blockquote>false, O arquivo nao pode ser movido</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro sera lancado uma excessao
     */
    public boolean moveOperation(String objIdPath, String folderDestinyId, String sourceFolderId) throws Exception
    {

        IDfMoveOperation mover = getSession().getMoveOperation();
        IDfSysObject obj = getSession().getIdfSysObject(objIdPath);

        if (!obj.isCheckedOut())
        {
            if (obj.isVirtualDocument())
            {
                mover.add(((IDfVirtualDocument) obj.asVirtualDocument("", false)));
            }
            else
            {
                mover.add(obj);
            }

            mover.setSourceFolderId(new DfId(sourceFolderId));
            mover.setDestinationFolderId(new DfId(folderDestinyId));

            if (isMonitorDeOperacao())
            {
                mover.setOperationMonitor(new monitorDeOperacoes());
            }
            return (mover.execute());
        }
        else
        {
            System.out.println("O Objeto [ " + objIdPath + " ] Esta em Check-Out");
            return (false);
        }
    }

    /**
     * Metodo que executa o Import de um arquivo local para a docbase
     * 
     * @param localFilePath O endereco do arquivo no PC
     * @param destinationFolderId A r_object_id da pasta de destino
     * 
     * @return	<blockquote>true, Importado com suscesso</blockquote>
     * 		<blockquote>false, Falha ao importar</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean importOperation(String localFilePath, String destinationFolderId) throws Exception
    {
        IDfImportOperation importOperation = getSession().getImportOperation();

        importOperation.setSession(getSession().getSession());
        importOperation.setDestinationFolderId(new DfId(destinationFolderId));

        IDfImportNode node = (IDfImportNode) importOperation.add(localFilePath);
        node.setFilePath(localFilePath);

        if (isMonitorDeOperacao())
        {
            importOperation.setOperationMonitor(new monitorDeOperacoes());
        }
        return (importOperation.execute());
    }

    /**
     * Metodo que executa o Import de um arquivo local para a docbase
     * 
     * @param localFilePath O endereco do arquivo no PC
     * @param destinationFolderId A r_object_id da pasta de destino
     * @param newName O novo nome do Objeto
     * 
     * @return	<blockquote>true, Importado com suscesso</blockquote>
     * 		<blockquote>false, Falha ao importar</blockquote>
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public boolean importOperation(String localFilePath, String destinationFolderId, String newName) throws Exception
    {
        IDfImportOperation importOperation = getSession().getImportOperation();

        importOperation.setSession(getSession().getSession());
        importOperation.setDestinationFolderId(new DfId(destinationFolderId));

        IDfImportNode node = (IDfImportNode) importOperation.add(localFilePath);
        node.setFilePath(localFilePath);
        node.setNewObjectName(newName);

        if (isMonitorDeOperacao())
        {
            importOperation.setOperationMonitor(new monitorDeOperacoes());
        }
        return (importOperation.execute());
    }

    /**
     * Metodo que executa um Export em um documento para o PC
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * @param format O formato a ser exportado {@link VarsDfc}
     * @param localDirectory O diretorio local para onde o arquivo deve ser exportado
     * 
     * @return Um Objeto File com o arquivo, caso o Exporte falhe, sera
     * retornado null
     * 
     * @throws Exception Caso algum erro ocorra uma excessao sera lancada
     */
    public File exportOperation(String objIdPath, String format, String localDirectory) throws Exception
    {

        IDfExportOperation exportOper = getSession().getExportOperation();
        exportOper.setDestinationDirectory(localDirectory);
        IDfExportNode expNode = (IDfExportNode) exportOper.add(getSession().getIdfPersistentObject(objIdPath));

        expNode.setFormat(format);

        if (exportOper.execute())
        {
            return new File(expNode.getFilePath());
        }
        return null;
    }

    public UidfSession getSession()
    {
        return session;
    }

    public void setSession(UidfSession session)
    {
        this.session = session;
    }

    public boolean isMonitorDeOperacao()
    {
        return monitorDeOperacao;
    }

    public void setMonitorDeOperacao(boolean monitorDeOperacao)
    {
        this.monitorDeOperacao = monitorDeOperacao;
    }

    public UIdfFile getFile()
    {
        return file;
    }

    public void setFile(UIdfFile file)
    {
        this.file = file;
    }

    /**
     * Classe de monitora de operacoes
     * 
     * @author deinf.dsouza
     *
     */
    class monitorDeOperacoes implements IDfOperationMonitor
    {

        public int progressReport(IDfOperation op, int iPercentOpDone, IDfOperationStep step, int iPercentStepDone, IDfOperationNode node) throws DfException
        {
            System.out.println("Etapa: " + step.getDescription() + " ==> " + iPercentOpDone + "%");
            return CONTINUE;
        }

        public int reportError(IDfOperationError error) throws DfException
        {

            System.out.println("Codigo de Erro : " + error.getErrorCode());
            System.out.println("Mensagem       : " + error.getMessage());
            System.out.println("Operacao       : " + error.getOperation());
            System.out.println("Nó             : " + error.getNode());

            return CONTINUE;
        }

        public int getYesNoAnswer(IDfOperationError question) throws DfException
        {
            int retval = 0;
            return retval;
        }
    }
}
