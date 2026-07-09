package com.example.android_apps.pertemuan_6.pertemuan_10

import android.Manifest // ➕ TAMBAHAN IMPORT BARU
import android.app.AlertDialog // ➕ TAMBAHAN IMPORT BARU
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText // ➕ TAMBAHAN IMPORT BARU
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts // ➕ TAMBAHAN IMPORT BARU
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_apps.data.api.RuangApiClient
import com.example.android_apps.data.model.RuangModel
import com.example.android_apps.databinding.FragmentHomeBinding
import com.example.android_apps.pertemuan_6.RuangAdapter // Menggunakan adapter dari pertemuan_6 agar tidak redundant
import com.example.android_apps.utils.PermissionHelper // ➕ TAMBAHAN IMPORT BARU
import com.example.android_apps.utils.ReminderHelper // ➕ TAMBAHAN IMPORT BARU
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // ➕ TAMBAHAN BARU: Launcher untuk pop-up izin notifikasi Android 13+
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ➕ TAMBAHAN BARU: Cek & minta izin notifikasi saat halaman diakses
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        // 1. Memanggil fungsi koneksi server saat pertama kali beranda dibuka
        loadStatusRuang()
        loadDaftarRuang()

        // 2. Aksi tombol refresh status peminjaman (Ditambahkan pemicu reminder dialog tanpa menghapus loadStatusRuang)
        binding.btnRefreshStatus.setOnClickListener {
            loadStatusRuang()
            showSetReminderDialog() // ➕ TAMBAHAN BARU: Memunculkan pop-up input menit pengingat
        }
    }

    private fun loadStatusRuang() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = RuangApiClient.apiService.getStatusRuang()
                binding.tvStatusRuang.text = "${response.namaRuang}: ${response.status}"
            } catch (e: Exception) {
                // Fallback teks jika endpoint status belum merespon sempurna
                binding.tvStatusRuang.text = "Aula Balai Desa: Tersedia (Siap Dipinjam Hari Ini)"
            }
        }
    }

    private fun loadDaftarRuang() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val listRuang = RuangApiClient.apiService.getDaftarRuang()
                val ruangAdapter = RuangAdapter(listRuang)
                binding.rvDaftarRuang.adapter = ruangAdapter
                binding.rvDaftarRuang.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                val listRuangDummy = listOf(
                    RuangModel(
                        id = "Aula Utama Balai Desa",
                        author = "Kapasitas: 300 Orang (Fasilitas AC)",
                        download_url = "https://images.unsplash.com/photo-1517457373958-b7bdd4587205?w=500"
                    ),
                    RuangModel(
                        id = "Ruang Rapat Anggota",
                        author = "Kapasitas: 50 Orang (Proyektor)",
                        download_url = "https://images.unsplash.com/photo-1577412647305-991150c7d163?w=500"
                    ),
                    RuangModel(
                        id = "Gedung Olahraga (GOR)",
                        author = "Kapasitas: 500 Orang (Fasilitas Lapangan)",
                        download_url = "https://images.unsplash.com/photo-1505373877841-8d25f7d46678?w=500"
                    ),
                    RuangModel(
                        id = "Lab Komputer Desa",
                        author = "Kapasitas: 30 Orang (Akses PC Internet)",
                        download_url = "https://images.unsplash.com/photo-1562774053-701939374585?w=500"
                    )
                )

                // Tampilkan data penyelamat dummy ini ke dalam Grid RecyclerView
                val ruangAdapter = RuangAdapter(listRuangDummy)
                binding.rvDaftarRuang.adapter = ruangAdapter
                binding.rvDaftarRuang.layoutManager = GridLayoutManager(requireContext(), 2)

                Toast.makeText(requireContext(), "Menampilkan katalog lokal (Server Offline)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ➕ TAMBAHAN BARU: Fungsi membuat Dialog pop-up input menit pengingat secara dinamis
    private fun showSetReminderDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Setel Pengingat Jadwal Ruang")
        builder.setMessage("Masukkan berapa menit dari sekarang sistem harus mengingatkan Anda:")

        val inputMinutes = EditText(requireContext())
        inputMinutes.hint = "Contoh: 1 atau 5"
        inputMinutes.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        builder.setView(inputMinutes)

        builder.setPositiveButton("Simpan Pengingat") { dialog, _ ->
            val minutesStr = inputMinutes.text.toString()
            if (minutesStr.isNotEmpty()) {
                val inputMenit = minutesStr.toInt()

                // Memanggil AlarmManager melalui Helper yang sudah dibuat
                ReminderHelper.setReminderMinutes(
                    context = requireContext(),
                    minutesFromNow = inputMenit,
                    title = "Peringatan Peminjaman Ruang",
                    message = "Jadwal masuk pinjam Aula Balai Desa Anda tiba! Silakan bersiap.",
                    targetActivityName = "com.example.android_apps.MainActivity"
                )

                Toast.makeText(requireContext(), "Pengingat disetel: $inputMenit Menit dari sekarang!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Menit tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Batal") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}