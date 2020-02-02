package jutildfc.utilsdfc;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.operations.IDfFile;
import com.documentum.operations.impl.DfFile;

public class UIdfFile
{
    /**
     * Metodo que remove a copia local do objeto em que foi feito Check-out
     * 
     * @param sysObj O Objeto que se deseja remover a copia local
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void removeLocalFile(IDfSysObject sysObj) throws Exception
    {
        IDfFile f = new DfFile(sysObj.getObjectName());
        f.deleteFile();
    }
}
