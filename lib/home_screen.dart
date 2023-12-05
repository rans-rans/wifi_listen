import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  bool wifiOn = false;
  final methodChannel = const MethodChannel("channel");

  @override
  void initState() {
    super.initState();
    methodChannel.setMethodCallHandler(
      (call) async {
        final args = call.arguments as int;
        wifiOn = args == 2;
        setState(() {});
        ScaffoldMessenger.of(context).clearSnackBars();
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            behavior: SnackBarBehavior.floating,
            backgroundColor: wifiOn ? Colors.green : Colors.red,
            content: Center(
              child: Text(wifiOn ? "Wifi is ON" : "wifi is off"),
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Listern Wifi"),
      ),
      body: Center(
          child: Text(
        wifiOn ? "Wifi is ON" : "wifi is off",
      )),
    );
  }
}
