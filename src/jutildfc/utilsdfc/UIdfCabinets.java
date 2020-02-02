package jutildfc.utilsdfc;

import jutildfc.data.VarsDfc;
import jutildfc.interfaces.AbstractUtilDfc;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.IDfId;

public class UIdfCabinets extends AbstractUtilDfc
{
    /**
     * Construtor padrao parametrizado
     * 
     * @param session O Objeto de sessao com o repositorio
     */
    public UIdfCabinets(UidfSession session)
    {
        super(session);
    }

    /**
     * Metodo que Cria um Gabinete no repositorio
     * 
     * @param nameCabinet O nome do Gabinete 
     * @param subjectCabinet O Assunto do gabinete
     * @return O r_object_id do Gabinete Criado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfId createCabinet(String nameCabinet, String subjectCabinet) throws Exception
    {

        IDfSysObject sysObj = getUidfSession().createNewObject(VarsDfc.OBJ_TYPE_DM_CABINET);
        sysObj.setObjectName(nameCabinet);
        sysObj.setSubject(subjectCabinet);
        sysObj.save();

        return (sysObj.getObjectId());
    }
}
