package com.afpa.utils;


import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import java.io.Serializable;

@SessionScoped
public class ViewUtils implements Serializable {


    public UIComponent getUIComponentOfId(UIComponent root, String id) {

        if (root.getId().equals(id)) {
            return root;
        }

        if (root.getChildCount() > 0) {

            for (UIComponent subUiComponent : root.getChildren()) {

                UIComponent returnComponent = getUIComponentOfId(subUiComponent, id);

                if (returnComponent != null) {

                    return returnComponent;
                }
            }
        }
        return null;
    }
}
