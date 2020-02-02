package jutildfc.utilsdfc;

public class UIdfType
{
    public UIdfType()
    {
    }

    /**
     * Metodo que cria a DQL da criacao de um tipo
     * 
     * @param typeName O nome do tipo
     * @param superName O nome do superTipo (heranca)
     * @param attrDefs Definicoes dos atributos
     * 
     * @return A DQL de criacao do tipo
     * @throws Exception Caso ocorra algum erro uma excessao sera lancada
     */
    public String createDQLStatement(String typeName, String superName, String attrDefs) throws Exception
    {
        String dqlStatement = "CREATE TYPE ".concat(typeName);
        if (attrDefs != null)
        {
            dqlStatement = dqlStatement.concat(" (").concat(attrDefs).concat(")");
        }
        dqlStatement = dqlStatement.concat(" WITH SUPERTYPE ").concat(superName);
        return dqlStatement;
    }
}
