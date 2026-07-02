package com.example.android_apps.pertemuan_6.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_apps.data.api.RuangApiClient
import com.example.android_apps.data.model.RuangModel
import com.example.android_apps.databinding.FragmentHomeBinding
import com.example.android_apps.pertemuan_6.RuangAdapter // Menggunakan adapter dari pertemuan_6 agar tidak redundant
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Memanggil fungsi koneksi server saat pertama kali beranda dibuka
        loadStatusRuang()
        loadDaftarRuang()

        // 2. Aksi tombol refresh status peminjaman
        binding.btnRefreshStatus.setOnClickListener {
            loadStatusRuang()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}