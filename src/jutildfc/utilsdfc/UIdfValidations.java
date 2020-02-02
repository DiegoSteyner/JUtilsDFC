package jutildfc.utilsdfc;

import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfType;
import com.documentum.fc.client.IDfValidator;

public class UIdfValidations
{
    /**
     * O Objeto de sessao
     */
    private UidfSession session = null;

    /**
     * Construtor Parametrizado
     * 
     * @param session O Objeto {@link UidfSession} configurado
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public UIdfValidations(UidfSession session)
    {
        this.session = session;
    }

    /**
     * Metodo que valida os atributos de um Objeto
     * 
     * @param objIdStr O r_object_id ou o Path do objeto
     * 
     * @param attrName O nome do atributo; Caso seja passado vazio ou nulo todos os atributos serao validados
     * 
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public void validate(String objIdStr, String attrName) throws Exception
    {
        IDfPersistentObject perObj = getSession().getIdfPersistentObject(objIdStr);
        IDfValidator validator = perObj.getValidator();
        IDfType type = perObj.getType();

        if (attrName == null || attrName.equals(""))
        {
            validator.validateAll(null, false);
        }
        else
        {
            if (type.findTypeAttrIndex(attrName) < 0)
            {
                System.out.println("O Atributo [".concat(attrName).concat("] Nao foi encontrado para validacao"));
                return;
            }
            validator.validateAttrRules(attrName, null, null);
        }
    }

    public UidfSession getSession()
    {
        return session;
    }

    public void setSession(UidfSession session)
    {
        this.session = session;
    }
}
