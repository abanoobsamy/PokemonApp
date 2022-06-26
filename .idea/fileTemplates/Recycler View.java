package 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ${Adapter_Name} extends RecyclerView.Adapter<${Adapter_Name}.${Name_View_Holder}> {

    private ArrayList<${Name_Model}> ${Name_Model}sList = new ArrayList<>();

    public void set${Name_Model}sList(ArrayList<${Name_Model}> ${Name_Model}sList) {
        this.${Name_Model}sList = ${Name_Model}sList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ${Name_View_Holder} onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        return new ${Name_View_Holder}(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.${Layout_Item}, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ${Name_View_Holder} holder, int position) {

        ${Name_Model} ${Name_Model} = ${Name_Model}sList.get(position);
    }

    @Override
    public int getItemCount() {
        return ${Name_Model}sList.size();
    }

    public class ${Name_View_Holder} extends RecyclerView.ViewHolder {

        public ${Name_View_Holder}(@NonNull View itemView) {
            super(itemView);
        }
    }
}
