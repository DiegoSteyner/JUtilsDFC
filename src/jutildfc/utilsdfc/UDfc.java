package jutildfc.utilsdfc;

import jutildfc.interfaces.AbstractUtilDfc;

import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.tools.RegistryPasswordUtils;

public class UDfc extends AbstractUtilDfc
{
    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto de sessao com o repositorio
     */
    public UDfc(UidfSession session)
    {
        super(session);
    }

    /**
     * Metodo que verifica se o objeto existe no repositorio
     * 
     * @param objIdPath O r_object_id ou o Path
     * 
     * @return Se True, O Objeto Existe
     * 
     */
    public boolean objectExist(String objIdPath)
    {
        try
        {
            return (getUidfSession().getIdfPersistentObject(objIdPath) != null);
        }
        catch (Exception e)
        {
            return (false);
        }
    }

    /**
     * Metodo que tenta forcar um save() em um objeto
     * 
     * @param sysObj O Objeto a ser forcado o salve()
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void forceSave(IDfSysObject sysObj) throws Exception
    {
        try
        {
            sysObj.save();
        }
        catch (Exception de)
        {
            try
            {
                sysObj.fetch(null);
                sysObj.save();
            }
            catch (Exception de2)
            {
                sysObj.revert();
                sysObj.save();
            }
        }
    }

    /**
     * Encrypts a password using {@link RegistryPasswordUtils}.
     * 
     * @param password the password to encrypt
     * 
     * @return {@link String} - the encrypted password
     * 
     * @throws DfException
     */
    public String encryptPassword(String password) throws Exception
    {
        return RegistryPasswordUtils.encrypt(password);
    }

    /**
     * Decrypts a password using {@link RegistryPasswordUtils}.
     * 
     * @param encryptedPassword the password to decrypt
     * 
     * @return {@link String} - the decrypted password
     * 
     * @throws DfException
     */
    public String decryptPassword(String encryptedPassword) throws Exception
    {
        return RegistryPasswordUtils.decrypt(encryptedPassword);
    }

    /**
     * Método que retorna a versão do dfc que está configurada no ClassPath
     * 
     * @return A versão do dfc
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public static String getDfcVersion() throws Exception
    {
        return DfClient.getDFCVersion();
    }
}
