package com.example.android_apps.pertemuan_6.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_apps.R // IMPORT INI UNTUK MENYAMBUNGKAN RESOURCE
import com.example.android_apps.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        // Nama drawable di bawah ini sudah disesuaikan persis dengan isi folder drawable Anda
        ProductModel("Tenda Utama Hajatan", "Tersedia • Max 3 Hari", R.drawable.tenda.toString()),
        ProductModel("Kursi Plastik (100 Unit)", "Tersedia • Paket", R.drawable.kursi.toString()),
        ProductModel("Sound System Lapangan", "Dipinjam • Balai Desa", R.drawable.sound.toString()),
        ProductModel("Mesin Fogging Nyamuk", "Tersedia • RT 04", R.drawable.fogging.toString()),
        ProductModel("Genset Daya 5000 Watt", "Tersedia • Emergency", R.drawable.genset.toString()),
        ProductModel("Mobil Ambulans Desa", "Siaga 24 Jam • Gratis", R.drawable.ambulance.toString()),

        // Barang di bawah ini sementara diarahkan ke balai_desa jika Anda belum memasukkan foto khususnya
        ProductModel("Proyektor & Layar Lebar", "Dipinjam • Karang Taruna", R.drawable.balai_desa.toString()),
        ProductModel("Baju Adat Pernikahan", "Tersedia • PKK", R.drawable.balai_desa.toString()),
        ProductModel("Cangkul & Sekop Paket", "Tersedia • Kerja Bakti", R.drawable.balai_desa.toString()),
        ProductModel("Meja Lipat Rapat (5 Unit)", "Tersedia • Ruang Rapat", R.drawable.balai_desa.toString()),

        ProductModel("Net & Bola Voli Paket", "Tersedia • Lapangan Barat", R.drawable.balai_desa.toString()),
        ProductModel("Gawang Futsal Portable", "Tersedia • Gedung Olahraga", R.drawable.balai_desa.toString()),
        ProductModel("Timbangan Balita Digital", "Tersedia • Posyandu Dahlia", R.drawable.balai_desa.toString()),
        ProductModel("Alat Ukur Tinggi Badan", "Tersedia • Posyandu", R.drawable.balai_desa.toString()),
        ProductModel("Termometer Inframerah", "Tersedia • Sekretariat", R.drawable.balai_desa.toString()),
        ProductModel("Terpal Biru Ukuran Besar", "Tersedia • Logistik", R.drawable.balai_desa.toString()),
        ProductModel("Mesin Potong Rumput", "Dipinjam • Dusun 2", R.drawable.balai_desa.toString()),
        ProductModel("Tangga Aluminium 5 Meter", "Tersedia • Fasilitas", R.drawable.balai_desa.toString()),
        ProductModel("Megaphone / Toa Genggam", "Tersedia • Pengumuman", R.drawable.balai_desa.toString()),
        ProductModel("Handy Talkie (5 Unit)", "Dipinjam • Keamanan", R.drawable.balai_desa.toString()),

        ProductModel("Gerobak Sampah", "Tersedia • Kebersihan", R.drawable.balai_desa.toString()),
        ProductModel("Pompa Air Alkon", "Tersedia • Pengairan Irigasi", R.drawable.balai_desa.toString()),
        ProductModel("Rompi Jaga Malam (10 Pcs)", "Tersedia • Poskamling", R.drawable.balai_desa.toString()),
        ProductModel("Senter Sorot Jarak Jauh", "Tersedia • Ronda", R.drawable.balai_desa.toString()),
        ProductModel("Jas Hujan Tim Siaga (5 Pcs)", "Tersedia • Penyelamatan", R.drawable.balai_desa.toString()),
        ProductModel("Papan Tulis Whiteboard", "Tersedia • Ruang Kelas Paud", R.drawable.balai_desa.toString()),
        ProductModel("Karpet Masjid Panjang", "Tersedia • Aula Keagamaan", R.drawable.balai_desa.toString()),
        ProductModel("Kipas Angin Dinding Besar", "Tersedia • Pendopo", R.drawable.balai_desa.toString()),
        ProductModel("Dispenser Galon Bawah", "Tersedia • Kantor Desa", R.drawable.balai_desa.toString()),
        ProductModel("Kotak P3K Lengkap", "Siaga • Keadaan Darurat", R.drawable.balai_desa.toString())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}