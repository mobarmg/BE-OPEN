/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.sherpa.submit;

import java.util.ArrayList;
import java.util.List;

import org.dspace.content.Metadatum;
import org.dspace.content.Item;
import org.dspace.core.Context;

public class MetadataValueISSNExtractor implements ISSNItemExtractor
{
    private List<String> metadataList;

    public void setMetadataList(List<String> metadataList)
    {
        this.metadataList = metadataList;
    }

    @Override
    public List<String> getISSNs(Context context, Item item)
    {
        List<String> values = new ArrayList<String>();
        for (String metadata : metadataList)
        {
            Metadatum[] Metadatums = item.getMetadataByMetadataString(metadata);
            for (Metadatum Metadatum : Metadatums)
            {
                String value = parseISSN(Metadatum.value);
                values.add(value);
            }
        }
        return values;
    }

    private String parseISSN(String issn) {

        if (issn.matches("^[0-9]{8}$")) {
            return issn.substring(0,4) + "-" + issn.substring(4,8);
        }

        return issn;
    }
}
