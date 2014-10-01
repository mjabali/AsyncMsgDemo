package mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class AsyncMsgBean {
    private String messageResult = "No messages received yet";

    public String getMessageResult() {
        return messageResult;
    }
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport (this);
    public AsyncMsgBean() {
    }

    public void sendAsyncMsg(ActionEvent actionEvent) {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                       "sendAsyncMessage",
                                                                       new Object[] { });
    }
    public void setMessageResult (String messageResult)
    {
      // When the barcode scanning is complete, the success callback of the Cordova
      // barcode scanning JavaScript function will invoke this method indirectly, by
      // evaluating the EL expression #{viewScope.BarcodeBean.barcodeResult}. It will
      // pass us the barcode that was scanned (or an error message).
      String oldMessageResult = "Bean:setMessageResult" + this.messageResult;
      this.messageResult = messageResult;
      propertyChangeSupport.firePropertyChange ("messageResult", oldMessageResult, messageResult);
    }
    
    public void addPropertyChangeListener (PropertyChangeListener l)
    {
      propertyChangeSupport.addPropertyChangeListener (l);
    }

    public void removePropertyChangeListener (PropertyChangeListener l)
    {
      propertyChangeSupport.removePropertyChangeListener (l);
    }
}
