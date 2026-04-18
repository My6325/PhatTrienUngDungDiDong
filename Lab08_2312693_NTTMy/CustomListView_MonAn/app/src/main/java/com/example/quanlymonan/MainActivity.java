package com.example.quanlymonan;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CONTEXT_MENU_EDIT   = 1;
    private static final int CONTEXT_MENU_DELETE = 2;

    // Ảnh drawable có sẵn — dùng cho dữ liệu mẫu và ảnh mặc định khi thêm mới
    private final int[] allImages = {
        R.drawable.img_banhmi, R.drawable.img_banhbo, R.drawable.img_banhu,
        R.drawable.img_bunbo, R.drawable.img_comboluclac, R.drawable.img_comboxao,
        R.drawable.img_comchienduabo, R.drawable.img_comchienduongchau,
        R.drawable.img_comgachiennuocmam, R.drawable.img_comgaluoc,
        R.drawable.img_hambuger, R.drawable.img_laucatam, R.drawable.img_laucua,
        R.drawable.img_mihoanhthanh, R.drawable.img_miquang
    };

    private List<MonAn> danhSachMonAn;
    private MonAnAdapter adapter;
    private int nextId;

    // Trạng thái tạm cho gallery picker (chia sẻ giữa dialog và launcher)
    private ImageView dialogImgRef;
    private Uri dialogSelectedUri;

    private final ActivityResultLauncher<String> imagePickerLauncher =
        registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null && dialogImgRef != null) {
                dialogSelectedUri = uri;
                dialogImgRef.setImageURI(uri);
            }
        });

    // ─────────────── Lifecycle ───────────────

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        danhSachMonAn = buildDanhSach();
        nextId = danhSachMonAn.size() + 1;

        ListView lvMonAn = findViewById(R.id.lvMonAn);
        adapter = new MonAnAdapter(this, danhSachMonAn);
        lvMonAn.setAdapter(adapter);
        registerForContextMenu(lvMonAn);
    }

    /** Tạo danh sách món ăn mẫu ban đầu */
    private List<MonAn> buildDanhSach() {
        String[] ten = {
            "Bánh mì", "Bánh bò", "Bánh ú", "Bún bò",
            "Cơm bò lúc lắc", "Cơm bò xào", "Cơm chiên dưa bò", "Cơm chiên dương châu",
            "Cơm gà chiên nước mắm", "Cơm gà luộc", "Hambuger",
            "Lẩu cá tằm", "Lẩu cua", "Mì hoành thánh", "Mì quảng"
        };
        String[] moTa = {
            "Bánh mì kẹp thịt truyền thống", "Bánh bò đủ màu", "",
            "Bún bò thường, xương", "Cơm, bò, ớt chuông", "", "", "",
            "Cơm gà nước mắm (đùi, cánh, nửa con)", "Cơm gà luộc (đùi, cánh, nửa con)",
            "Bánh mì kẹp thịt tròn", "Lẩu nguyên con, nửa con", "", "", ""
        };
        int[] gia = {
            12000, 10000, 15000, 35000, 40000, 45000, 40000, 35000,
            35000, 40000, 30000, 300000, 250000, 30000, 30000
        };

        List<MonAn> ds = new ArrayList<>();
        int count = Math.min(Math.min(ten.length, moTa.length), Math.min(gia.length, allImages.length));
        for (int i = 0; i < count; i++) {
            ds.add(new MonAn(i + 1, ten[i], moTa[i], gia[i], allImages[i]));
        }
        return ds;
    }

    // ─────────────── Options Menu ───────────────

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            showMonAnDialog(null, -1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ─────────────── Context Menu ───────────────

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(danhSachMonAn.get(info.position).getTen());
        menu.add(0, CONTEXT_MENU_EDIT,   0, "✏️  Sửa món ăn");
        menu.add(0, CONTEXT_MENU_DELETE, 1, "🗑️  Xóa món ăn");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info == null) return super.onContextItemSelected(item);

        if (item.getItemId() == CONTEXT_MENU_EDIT) {
            showMonAnDialog(danhSachMonAn.get(info.position), info.position);
            return true;
        } else if (item.getItemId() == CONTEXT_MENU_DELETE) {
            deleteMonAn(info.position);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    // ─────────────── Dialog Thêm / Sửa (gộp chung) ───────────────

    /**
     * Hiển thị dialog thêm hoặc sửa món ăn.
     * @param existing null → chế độ Thêm; khác null → chế độ Sửa
     * @param position vị trí trong danh sách (chỉ dùng khi isEdit)
     */
    private void showMonAnDialog(MonAn existing, int position) {
        boolean isEdit = (existing != null);

        View dialogView = getLayoutInflater().inflate(R.layout.add_monan, null);
        TextView txtTitle  = dialogView.findViewById(R.id.txtTitle);
        EditText edtTen    = dialogView.findViewById(R.id.edtTenMon);
        EditText edtMoTa   = dialogView.findViewById(R.id.edtMoTa);
        EditText edtGia    = dialogView.findViewById(R.id.edtGia);
        ImageView imgMonAn = dialogView.findViewById(R.id.imgMonAn);

        txtTitle.setText(isEdit ? "SỬA MÓN ĂN" : "THÊM MÓN ĂN MỚI");

        if (isEdit) {
            edtTen.setText(existing.getTen());
            edtMoTa.setText(existing.getMoTa());
            edtGia.setText(String.valueOf(existing.getGia()));
            if (existing.getHinhUri() != null) {
                dialogSelectedUri = Uri.parse(existing.getHinhUri());
                imgMonAn.setImageURI(dialogSelectedUri);
            } else {
                dialogSelectedUri = null;
                imgMonAn.setImageResource(existing.getHinhResId());
            }
        } else {
            dialogSelectedUri = null;
            imgMonAn.setImageResource(R.drawable.ic_launcher_background);
        }

        dialogImgRef = imgMonAn;
        imgMonAn.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).create();

        dialogView.findViewById(R.id.btnHuy).setOnClickListener(v -> {
            dialogImgRef = null;
            dialog.dismiss();
        });

        dialogView.findViewById(R.id.btnLuu).setOnClickListener(v -> {
            String ten    = edtTen.getText().toString().trim();
            String moTa   = edtMoTa.getText().toString().trim();
            String giaStr = edtGia.getText().toString().trim();

            if (ten.isEmpty())    { edtTen.setError("Vui lòng nhập tên món ăn"); return; }
            if (giaStr.isEmpty()) { edtGia.setError("Vui lòng nhập giá tiền");   return; }

            int id  = isEdit ? existing.getId() : nextId++;
            int gia = Integer.parseInt(giaStr);

            MonAn monAn;
            if (dialogSelectedUri != null) {
                monAn = new MonAn(id, ten, moTa, gia, dialogSelectedUri.toString());
            } else if (isEdit) {
                monAn = new MonAn(id, ten, moTa, gia, existing.getHinhResId());
            } else {
                monAn = new MonAn(id, ten, moTa, gia, allImages[0]);
            }

            if (isEdit) {
                danhSachMonAn.set(position, monAn);
                Toast.makeText(this, "Đã cập nhật: " + ten, Toast.LENGTH_SHORT).show();
            } else {
                danhSachMonAn.add(monAn);
                Toast.makeText(this, "Đã thêm: " + ten, Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
            dialogImgRef = null;
            dialog.dismiss();
        });

        dialog.show();
        Toast.makeText(this,
            isEdit ? "Nhấn vào ảnh để đổi từ thư viện" : "Nhấn vào ảnh để chọn từ thư viện",
            Toast.LENGTH_SHORT).show();
    }

    // ─────────────── Xóa ───────────────

    private void deleteMonAn(int position) {
        String ten = danhSachMonAn.get(position).getTen();
        new AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Bạn có chắc muốn xóa \"" + ten + "\" không?")
            .setPositiveButton("Xóa", (d, w) -> {
                danhSachMonAn.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã xóa: " + ten, Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Hủy", null)
            .show();
    }
}
