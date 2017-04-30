## AiCore Structure

### App Instance
- Heartbeat	(Heart thread)
- Database	(MySQL backend)
- InputProcessing
  - NetworkProcessing
      - DataListener
      - TypeListener
  - TerminalProcessing
      - TerminalCommands
           - {TermCommands}
           - ...
      - TerminalExecutes (iFace)
- Settings

