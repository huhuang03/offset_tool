package com.th.offsettool.mpv.main

import com.th.offsettool.bean.DataMain
import com.th.offsettool.util.DEFAULT_FILE_NAME
import com.th.offsettool.util.FILE_FILTER
import javafx.stage.FileChooser
import java.awt.*
import java.io.File
import javax.swing.*
import javax.swing.table.AbstractTableModel


class UIMain(val presenter: ContactMain.Presenter): ContactMain.View {
    private val root = JFrame()
    private val table = UITable()
    private var addPanel = AddPanel(object : AddPanel.OnAddListener {
        override fun onAddBase(base: String) {
            presenter.addBase(base)
        }

        override fun onAddOffset(offset: String) {
            presenter.addOffset(offset)
        }
    })

    init {
        initMenu()
        presenter.setView(this)
        root.size = Dimension(800, 600)
        root.add(addPanel.root, BorderLayout.NORTH)
        table.addMe(root)
        root.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        root.isVisible = true
    }

    private fun initMenu() {
        this.root.menuBar = MenuBar().apply {
            add(Menu("File").apply {
                add(MenuItem("Save").apply {
                    addActionListener {
                        fileChooser { chooser, file ->
                            chooser.showSaveDialog(chooser)
                            presenter.saveFile(file)
                        }
                    }
                })

                add(MenuItem("Load").apply {
                    addActionListener {
                        fileChooser { chooser, file ->
                            chooser.showOpenDialog(chooser)
                            presenter.loadFile(file)
                        }
                    }
                })

            })
        }
    }

    private fun fileChooser(callback: (chooser: JFileChooser, file: File?) -> Unit) {
        JFileChooser(DEFAULT_FILE_NAME).apply {
            this.fileFilter = FILE_FILTER
            callback.invoke(this, selectedFile)
        }
    }

    override fun updateUI(dataMain: DataMain) {
        table.updateUI(dataMain)
    }

}

class AddPanel(onAddListener: OnAddListener) {
    val root = JPanel()

    interface OnAddListener {
        fun onAddBase(base: String)
        fun onAddOffset(offset: String)
    }

    init {
        root.layout = BoxLayout(root, BoxLayout.Y_AXIS)
        addOpPanel("添加基址: ") {
            onAddListener.onAddBase(it)
        }

        addOpPanel("添加偏移: ") {
            onAddListener.onAddOffset(it)
        }
    }

    private fun addOpPanel(name: String, onAddCallback: (value: String)-> Unit) {
        val container = JPanel()
        container.layout = FlowLayout()
        container.add(JLabel(name))
        val jTextField = JTextField(20)
        container.add(jTextField)
        val jButton = JButton("添加")
        container.add(jButton)
        root.add(container)


        jButton.addActionListener {
            val text = jTextField.text
            if (text.isNotBlank()) {
                onAddCallback.invoke(text)
            }
        }
    }
}


class UITable {
    private val table = JTable()
    private val scrollWrapper: JScrollPane = JScrollPane(table)
    var dataMain: DataMain? = null

    init {
        table.model = object: AbstractTableModel() {
            override fun getRowCount(): Int {
                return dataMain?.mOffsets?.size?: 0
            }

            override fun getColumnCount(): Int {
                println("getColumnCount: ${dataMain?.mBases?.size?: 0}")
                println("getColumnCount: $dataMain")
                return 1 + (dataMain?.mBases?.size?: 0)
            }

            override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
                return if (columnIndex == 0) {
                    dataMain!!.mOffsets[rowIndex].show(0)
                } else {
                    dataMain!!.getValue(columnIndex - 1, rowIndex).show()
                }
            }

            override fun getColumnName(column: Int): String {
                if (column == 0) {
                    return "offset\\base";
                }
                return "base$column"
            }
        }
    }

    fun addMe(jFrame: JFrame) {
        jFrame.add(scrollWrapper, BorderLayout.CENTER)
    }

    fun updateUI(dataMain: DataMain) {
        println("updateUI")
        this.dataMain = dataMain
        (this.table.model as AbstractTableModel).fireTableStructureChanged()
    }

}
