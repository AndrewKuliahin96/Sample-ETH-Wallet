package com.example.centerprimesampleethsdk.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.centerprimesampleethsdk.erc_token_balance.CheckERCTokenBalanceActivity
import com.example.centerprimesampleethsdk.create_wallet.CreateWalletActivity
import com.example.centerprimesampleethsdk.export_keystore.ExportKeyStoreActivity
import com.example.centerprimesampleethsdk.export_private_key.ExportPrivateKeyActivity
import com.example.centerprimesampleethsdk.import_by_private_key.ImportByPrivateKeyActivity
import com.example.centerprimesampleethsdk.import_wallet_from_keystore.ImportWalletFromKeyStoreActivity
import com.example.centerprimesampleethsdk.send_erc_token.SendERCTokenActivity
import com.example.centerprimesampleethsdk.send_eth.SendEthActivity
import com.example.centerprimesampleethsdk.check_balance.CheckBalanceActivity
import com.example.centerprimesampleethsdk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding) {
            createBtn.setOnClickListener { startActivity(CreateWalletActivity::class.java) }

            importBtn.setOnClickListener { startActivity(ImportWalletFromKeyStoreActivity::class.java) }

            checkBalance.setOnClickListener { startActivity(CheckBalanceActivity::class.java) }

            exportKeystore.setOnClickListener { startActivity(ExportKeyStoreActivity::class.java) }

            importByPrivatekey.setOnClickListener { startActivity(ImportByPrivateKeyActivity::class.java) }

            exportPrivateKey.setOnClickListener { startActivity(ExportPrivateKeyActivity::class.java) }

            sendEth.setOnClickListener { startActivity(SendEthActivity::class.java) }

            checkERCTokenkBalance.setOnClickListener { startActivity(CheckERCTokenBalanceActivity::class.java) }

            sendERCToken.setOnClickListener { startActivity(SendERCTokenActivity::class.java) }
        }
    }

    private fun <T : AppCompatActivity> startActivity(activityClass: Class<T>) {
        startActivity(Intent(this, activityClass))
    }
}
