package ${className};

import java.util.Date;

public class ${className} {

<#list elements as element>
    private ${element.type} ${element.name};

    public ${element.type} get${element.name?cap_first}() {
        return ${element.name};
    }

    public void set${element.name?cap_first}(${element.type} ${element.name}) {
        this.${element.name} = ${element.name};
    }
 </#list>
}