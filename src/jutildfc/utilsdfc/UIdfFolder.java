package jutildfc.utilsdfc;

import jutildfc.data.VarsDfc;
import jutildfc.interfaces.AbstractUtilDfc;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;

public class UIdfFolder extends AbstractUtilDfc
{
    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto {@link UidfSession} configurado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UIdfFolder(UidfSession session) throws Exception
    {
        super(session);
    }

    /**
     * Metodo que cria uma pasta dentro da pasta especificada
     * 
     * @param saveInPath O Local onde a pasta deve ser criada
     * @param nameFolder O nome da pasta
     * 
     * @return O Objeto IDfFolder da pasta criada
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfFolder createFolder(String saveInPath, String nameFolder) throws Exception
    {
        IDfFolder folder = (IDfFolder) getUidfSession().createNewObject(VarsDfc.OBJ_TYPE_DM_FOLDER);

        folder.setObjectName(nameFolder);
        folder.link((!saveInPath.endsWith("/") ? saveInPath : saveInPath.substring(0, saveInPath.length() - 1)));
        folder.save();

        return (folder);
    }

    /**
     * Metodo que retorna os dados dos objetos dentro da pasta
     * 
     * @param objIdPath O r_object_id ou o Path do objeto
     * 
     * @return Uma Collection com os objetos dentro da pasta
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public IDfCollection getObjectsInFolder(String objIdPath) throws Exception
    {
        return (((IDfFolder) getUidfSession().getIdfPersistentObject(objIdPath)).getContents(null));
    }

    /**
     * Metodo que procura um objeto pelo nome dentro de uma pasta
     * 
     * @param folderPath A pasta em que o objeto esta 
     * @param nameObject O nome do objeto detro da pasta
     * 
     * @return O r_object_id do objeto caso ele seja encontrado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String getIdByObjectNameInFolder(String folderPath, String nameObject) throws Exception
    {
        UIdfQuery query = new UIdfQuery(getUidfSession());
        String dql = VarsDfc.DQL_OBJECT_IN_FOLDER.replace(VarsDfc.REPLACE_NAME_DQL, nameObject).replace(VarsDfc.REPLACE_PATH_DQL, folderPath);

        return (UIdfCollections.getAttributeValue(query.execQuery(dql), VarsDfc.ATTR_OBJECT_ID, true).asString());
    }

    /**
     * Metodo que retorna o Path de um objeto ou o r_object_id
     * da pasta em que ele esta dentro
     * 
     * @param objId O r_object_id do objeto
     * 
     * @param idOrPath 	<blockquote>true, O r_object_id da pasta</blockquote>
     * 			<blockquote>false, O r_folder_path da pasta</blockquote>
     * 
     * @return O Path da pasta de onde o objeto esta
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String getIdFolderOrPathObject(String objId, boolean idOrPath) throws Exception
    {
        if (idOrPath)
        {
            return (((IDfFolder) getUidfSession().getIdfSysObject(getUidfSession().getIdfSysObject(objId).getFolderId(0).getId())).getObjectId().getId());
        }
        else
        {
            return (((IDfFolder) getUidfSession().getIdfSysObject(getUidfSession().getIdfSysObject(objId).getFolderId(0).getId())).getFolderPath(0));
        }
    }
}
