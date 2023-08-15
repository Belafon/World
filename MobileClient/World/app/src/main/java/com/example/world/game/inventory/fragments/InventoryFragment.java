package com.example.world.game.inventory.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.world.game.inventory.Inventory;
import com.example.world.game.visibles.items.Item;
import com.example.world.game.visibles.items.ItemInfoFragment;
import java.util.List;






public class InventoryFragment extends Fragment implements ItemAdapter.OnItemClickListener {
    public final int fragmentContainer;
    public final Inventory inventory;
    public InventoryFragment(Inventory inventory, int fragmentContainer) {
        this.inventory = inventory;
        this.fragmentContainer = fragmentContainer;
    }

    @Override
    public void onItemClick(Item item) {
        Fragment newFragment = new ItemInfoFragment(this. fragmentContainer, item);

        requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(fragmentContainer, newFragment)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemAdapter adapter = new ItemAdapter(inventory.items.values(), this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item.getId(), item.getType(), item.getName(), item.getWeight(),
                item.getVisibility(), item.getToss(), item.getArgs(), item.getBehaviours());

            if (recyclerView != null && recyclerView.getAdapter() != null) {
            ((ItemAdapter) recyclerView.getAdapter()).addItem(item);
        }
    }

    public void removeItemFromInventory(Item item) {
        inventory.removeItem(panels, item.getId());
        
        // Update the RecyclerView adapter
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            ((ItemAdapter) recyclerView.getAdapter()).removeItem(item);
        }
    }


    public static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

        private List<Item> itemList;
        private OnItemClickListener itemClickListener;
    
        public ItemAdapter(List<Item> itemList, OnItemClickListener listener) {
            this.itemList = itemList;
            this.itemClickListener = listener;
        }

        public interface OnItemClickListener {
            void onItemClick(Item item);
        }

    
        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inventory_item, parent, false);
            return new ItemViewHolder(itemView);
        }
    
        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.bind(item, itemClickListener);
        }
    
        @Override
        public int getItemCount() {
            return itemList.size();
        }
    
        public static class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView itemNameTextView;
    
            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                itemNameTextView = itemView.findViewById(R.id.text_view_item);
            }
               
            public void bind(Item item, OnItemClickListener listener) {
                itemNameTextView.setText(item.getName());
                itemView.setOnClickListener(view -> {
                    if (listener != null) {
                        listener.onItemClick(item);
                    }
                });
            }
        }

        public void addItem(Item item) {
            itemList.add(item);
            notifyItemInserted(itemList.size() - 1);
        }
    
        public void removeItem(Item item) {
            int position = itemList.indexOf(item);
            if (position != -1) {
                itemList.remove(position);
                notifyItemRemoved(position);
            }
        }


    }
}